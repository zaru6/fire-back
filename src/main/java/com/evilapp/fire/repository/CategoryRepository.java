package com.evilapp.fire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evilapp.fire.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
