package com.evilapp.fire.model;

import jakarta.persistence.Column;

public class ProductTable {

    @Column // Ensures the column is not nullable and sets max length
    private String name;

    @Column
    private Integer price;

    @Column
    private boolean available;

    @Column
    private String categoryName;

    @Column
    private String subcategoryName;

    public ProductTable() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

}
