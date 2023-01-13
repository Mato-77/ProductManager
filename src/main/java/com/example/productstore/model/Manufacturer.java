package com.example.productstore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "manufactures",schema = "public",catalog = "store")
@NoArgsConstructor
public class Manufacturer {

    @Id
    private String name;

    public Manufacturer(String name) {
        this.name = name;
    }
}
