package com.example.productstore.web.rest;


import com.example.productstore.model.Category;
import com.example.productstore.model.dto.CategoryDTO;
import com.example.productstore.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return this.categoryService.findAll();
    }

    @GetMapping("/ordered")
    public List<CategoryDTO> getCategoriesOrdered(){
        return this.categoryService.findAllCategoriesWithProducts();
    }

}
