package com.evilapp.fire.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.evilapp.fire.dtos.ProductDto;

public class ProductDtoMapper implements RowMapper<ProductDto> {

    @Override
    @Nullable
    public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductDto productDto = new ProductDto();
        productDto.setId(rs.getLong("id"));
        productDto.setName(rs.getString("product_name"));
        productDto.setPrice(rs.getInt("product_price"));
        productDto.setAvailable(rs.getBoolean("is_available"));
        productDto.setCategoryLabel(rs.getString("category_label"));
        productDto.setSubcategoryLabel(rs.getString("subcategory_label"));
        productDto.setCreatedBy(rs.getString("created_by"));
        productDto.setCreatedAt(rs.getTimestamp("created_at"));
        productDto.setUpdatedAt(rs.getTimestamp("updated_at"));
        return productDto;
    }

}
