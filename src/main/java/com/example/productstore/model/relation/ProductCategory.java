package com.example.productstore.model.relation;

import com.example.productstore.model.Category;
import com.example.productstore.model.Product;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "product_categories",schema = "public",catalog = "store")
@NoArgsConstructor
public class ProductCategory {

    @EmbeddedId
    @Column(nullable = false, unique = true)
    private ProductCategoryPK id;

    @ManyToOne()
    @MapsId("category_name")
    @JoinColumn(name = "category_name")
    private Category category;


    @ManyToOne()
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;


    public ProductCategory(Category category, Product product) {
        this.category = category;
        this.product = product;
        this.id = new ProductCategoryPK(product.getId(),category.getName());
    }
}
