package com.evilapp.fire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.evilapp.fire.dao.JdbcFireDao;
import com.evilapp.fire.model.CsvData;
import com.evilapp.fire.model.Product;
import com.evilapp.fire.repository.ProductRepository;
import com.evilapp.fire.service.CsvDataService;
import com.evilapp.fire.service.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private final JdbcFireDao jdbcFireDao;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public FileUploadController(
        JdbcFireDao jdbcFireDao, 
        ProductRepository productRepository,
        UserService userService) {
        this.jdbcFireDao = jdbcFireDao;
        this.productRepository = productRepository;
        this.userService = userService; 
    }

    @PostMapping("/csv")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload a file!", HttpStatus.BAD_REQUEST);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            List<Product> productCsvList = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Product product = new Product();
                product.setName(values[0]);
                product.setPrice(Integer.parseInt(values[1]));
                product.setSubcategoryId(Integer.parseInt(values[2]));
                product.setAvailable(Boolean.parseBoolean(values[3]));
                product.setCreatedBy(userService.getAuthenticatedUser().getId());
                productCsvList.add(product);
            }
            for(Product product : productCsvList) { //za lakše debugiranje
                productRepository.save(product);
            }

            return new ResponseEntity<>("File uploaded and processed successfully!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
