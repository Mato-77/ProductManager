package com.example.productstore.service;

import com.example.productstore.model.Category;
import com.example.productstore.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    List<CategoryDTO> findAllCategoriesWithProducts();
}
