package com.evilapp.fire.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.evilapp.fire.model.ProductTable;

public class ProductTableMapper implements RowMapper<ProductTable> {

    @Override
    @Nullable
    public ProductTable mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductTable productTable = new ProductTable();
        productTable.setId(rs.getLong("id"));
        productTable.setName(rs.getString("product_name"));
        productTable.setPrice(rs.getInt("product_price"));
        productTable.setAvailable(rs.getBoolean("is_available"));
        productTable.setCategoryLabel(rs.getString("category_label"));
        productTable.setSubcategoryLabel(rs.getString("subcategory_label"));
        return productTable;
    }

}
