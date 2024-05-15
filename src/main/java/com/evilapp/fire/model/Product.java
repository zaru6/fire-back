package com.evilapp.fire.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products", schema = "models") // Specifies the table name and schema
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses the database's SERIAL type
    private Long id;

    @Column // Ensures the column is not nullable and sets max length
    private String name;

    @Column
    private Integer price;

    @Column
    private Boolean available;

    public Product() {
    }

    public Product(Long id, String name, Integer price, Boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    } 

}
