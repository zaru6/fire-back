package com.evilapp.fire.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.evilapp.fire.model.Product;

public class ProductMapper implements RowMapper<Product> {

    @Override
    @Nullable
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));   
        product.setName(rs.getString("name"));    
        product.setPrice(rs.getInt("price"));            
        product.setSubcategoryId(rs.getInt("subcategory_id"));        
        product.setAvailable(rs.getBoolean("available"));
        return product;
    }

}
