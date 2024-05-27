package com.evilapp.fire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evilapp.fire.model.Subcategory;
import com.evilapp.fire.repository.SubcategoryRepository;

@Service
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepository.findAll();
    }

}
