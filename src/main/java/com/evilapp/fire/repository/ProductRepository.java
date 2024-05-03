package com.evilapp.fire.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evilapp.fire.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
