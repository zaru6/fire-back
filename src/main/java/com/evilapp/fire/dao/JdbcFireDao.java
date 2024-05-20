package com.evilapp.fire.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.mapper.ProductTableMapper;
import com.evilapp.fire.model.ProductTable;

@Repository
public class JdbcFireDao {


    private final String SQL_GET_PRODUCTS_FOR_TABLE = "select p.name AS product_name, p.price AS product_price, p.available AS is_available,c.label AS category_label, s.label AS subcategory_label from models.products p join models.subcategories s on p.subcategory_id=s.id join models.categories c on s.category_id=c.id";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcFireDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductTable> getProductsForTable() {
        return jdbcTemplate.query(SQL_GET_PRODUCTS_FOR_TABLE, new ProductTableMapper());
    }

}
