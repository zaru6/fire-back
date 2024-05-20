package com.evilapp.fire.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.evilapp.fire.model.Category;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    @Nullable
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));   
        category.setName(rs.getString("name"));    
        category.setLabel(rs.getString("label")); 
        return category;
    }

}
