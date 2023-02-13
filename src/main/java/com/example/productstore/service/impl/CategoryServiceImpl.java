package com.example.productstore.service.impl;

import com.example.productstore.model.Category;
import com.example.productstore.model.dto.CategoryDTO;
import com.example.productstore.model.dto.CategoryOrManufactureSaved;
import com.example.productstore.repository.CategoryRepository;
import com.example.productstore.repository.ProductCategoryRepository;
import com.example.productstore.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductCategoryRepository productCategoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductCategoryRepository productCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productCategoryRepository = productCategoryRepository;
    }


    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<CategoryDTO> findAllCategoriesWithProducts() {
        return this.productCategoryRepository.findAllByGroupedCategories();
    }

    @Override
    public Optional<CategoryOrManufactureSaved> saveCategory(String name) {
        this.categoryRepository.save(new Category(name));
        return Optional.of(new CategoryOrManufactureSaved(name));
    }
}
