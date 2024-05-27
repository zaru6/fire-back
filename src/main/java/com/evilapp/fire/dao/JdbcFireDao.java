package com.evilapp.fire.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.dtos.ProductDto;
import com.evilapp.fire.mapper.ProductDtoMapper;

@Repository
public class JdbcFireDao {


    private final String SQL_GET_PRODUCTS_FOR_TABLE = "select p.id, p.name AS product_name, p.price AS product_price, p.available AS is_available,c.label AS category_label, s.label AS subcategory_label, u.username as created_by, p.created_at, p.updated_at from models.products p join models.subcategories s on p.subcategory_id=s.id join models.categories c on s.category_id=c.id join users u on p.created_by = u.id";

    private final String SQL_INSERT_PRODUCT = "INSERT INTO models.products (name, price, subcategory_id, available) VALUES (?, ?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcFireDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductDto> getProductsForTable() {
        return jdbcTemplate.query(SQL_GET_PRODUCTS_FOR_TABLE, new ProductDtoMapper());
    }

    public int insertProduct(String name, int price, int subcategoryId, boolean available) {
        return jdbcTemplate.update(SQL_INSERT_PRODUCT, name, price, subcategoryId, available);
    }

}
