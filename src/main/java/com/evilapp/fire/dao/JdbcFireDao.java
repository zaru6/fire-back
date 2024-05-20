package com.evilapp.fire.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.mapper.ProductMapper;
import com.evilapp.fire.model.Product;

@Repository
public class JdbcFireDao {


    private final String SQL_GET_PRODUCTS_FOR_TABLE = "select * from models.products";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcFireDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getProductsForTable() {
        return jdbcTemplate.query(SQL_GET_PRODUCTS_FOR_TABLE, new ProductMapper());
    }

}
