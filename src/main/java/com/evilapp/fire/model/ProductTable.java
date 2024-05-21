package com.evilapp.fire.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class ProductTable {

    @Id // Ensures the column is not nullable and sets max length
    private Long id;

    @Column // Ensures the column is not nullable and sets max length
    private String name;

    @Column
    private Integer price;

    @Column
    private boolean available;

    @Column
    private String categoryLabel;

    @Column
    private String subcategoryLabel;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getSubcategoryLabel() {
        return subcategoryLabel;
    }

    public void setSubcategoryLabel(String subcategoryLabel) {
        this.subcategoryLabel = subcategoryLabel;
    }

    

}
