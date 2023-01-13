package com.example.productstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    private String name;

    private Long total;
}
