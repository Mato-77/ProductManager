package com.example.productstore.model.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductCategoryPK implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "category_name")
    private String category;


}
