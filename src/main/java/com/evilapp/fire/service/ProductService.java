package com.evilapp.fire.service;

import com.evilapp.fire.dao.JdbcFireDao;
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

    @Autowired
    public ProductService(ProductRepository productRepository, JdbcFireDao jdbcFireDao) {
        this.productRepository = productRepository;
        this.jdbcFireDao = jdbcFireDao;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsForTable() {
        return jdbcFireDao.getProductsForTable();
    }
}
