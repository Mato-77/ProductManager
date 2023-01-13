package com.example.productstore.model.dto;

import com.example.productstore.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductSavedDTO implements Serializable {
    private String name;
    private String description;
    private Long quantity;
    private String manufacturerName;
    private List<Category> categoriesNames;
}

