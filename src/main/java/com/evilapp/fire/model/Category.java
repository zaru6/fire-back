package com.evilapp.fire.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories", schema = "models")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses the database's SERIAL type
    private Long id;

    @Column // Ensures the column is not nullable and sets max length
    private String name;

    @Column
    private String label;

    public Category() {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
