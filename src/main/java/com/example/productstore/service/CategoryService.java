package com.example.productstore.service;

import com.example.productstore.model.Category;
import com.example.productstore.model.dto.CategoryDTO;
import com.example.productstore.model.dto.CategoryOrManufactureSaved;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    List<CategoryDTO> findAllCategoriesWithProducts();

    Optional<CategoryOrManufactureSaved> saveCategory(String name);
}
