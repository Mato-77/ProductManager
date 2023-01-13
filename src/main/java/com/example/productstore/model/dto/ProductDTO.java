package com.example.productstore.model.dto;

import com.example.productstore.model.Category;
import com.example.productstore.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductDTO {

    private Product product;

    private List<Category> categories;
}
