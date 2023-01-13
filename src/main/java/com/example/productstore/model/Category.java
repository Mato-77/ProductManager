package com.example.productstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "categories",schema = "public",catalog = "store")
@NoArgsConstructor
public class Category {

    @Id
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
