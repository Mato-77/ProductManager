package com.example.productstore.model;

import com.example.productstore.model.relation.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "products",schema = "public",catalog = "store")
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    private Manufacturer manufacturer;




    @Transient
    private List<Category> categories;

    public Product(String name, String description, Long quantity, Manufacturer manufacturer, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.categories = categories;
    }
}
