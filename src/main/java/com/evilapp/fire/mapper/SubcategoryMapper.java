package com.evilapp.fire.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.evilapp.fire.model.Subcategory;

public class SubcategoryMapper implements RowMapper<Subcategory> {

    @Override
    @Nullable
    public Subcategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subcategory subcategory = new Subcategory();
        subcategory.setId(rs.getLong("id"));   
        subcategory.setName(rs.getString("name"));    
        subcategory.setLabel(rs.getString("label")); 
        subcategory.setCategoryId(rs.getLong("category_id"));
        return subcategory;
    }
}
