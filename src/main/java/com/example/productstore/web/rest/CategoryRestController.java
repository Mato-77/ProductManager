package com.example.productstore.web.rest;


import com.example.productstore.model.Category;
import com.example.productstore.model.dto.CategoryDTO;
import com.example.productstore.model.dto.CategoryOrManufactureSaved;
import com.example.productstore.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*",allowedHeaders = "*")
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

    @PostMapping("/add")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity<CategoryOrManufactureSaved> addCategory(@RequestBody CategoryOrManufactureSaved categoryOrManufactureSaved){

        return this.categoryService
                .saveCategory(categoryOrManufactureSaved.getName())
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
