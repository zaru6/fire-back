package com.evilapp.fire.service;

import com.evilapp.fire.dao.JdbcFireDao;
import com.evilapp.fire.dtos.ProductDto;
import com.evilapp.fire.model.Product;
import com.evilapp.fire.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final JdbcFireDao jdbcFireDao;
    private final UserService userService;

    @Autowired
    public ProductService(ProductRepository productRepository, JdbcFireDao jdbcFireDao, UserService userService) {
        this.productRepository = productRepository;
        this.jdbcFireDao = jdbcFireDao;
        this.userService = userService;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        product.setCreatedBy(userService.getAuthenticatedUser().getId());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getProductsForTable() {
        return jdbcFireDao.getProductsForTable();
    }
}
