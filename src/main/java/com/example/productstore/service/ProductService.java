package com.example.productstore.service;

import com.example.productstore.exception.CategoryNotExistException;
import com.example.productstore.exception.ManufacturerNotExistException;
import com.example.productstore.exception.ProductNotExistException;
import com.example.productstore.model.Product;
import com.example.productstore.model.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();

   Optional<ProductDTO> findById(Long id) throws ProductNotExistException;

    Optional<ProductDTO> saveProduct(String name, String description, Long quantity, String manufacturerName,
                                  List<String> categoriesNames) throws ManufacturerNotExistException, CategoryNotExistException;

    Optional<ProductDTO> editProduct(Long id, String name, String description, Long quantity,
                        String manufacturerName, List<String> categoriesNames) throws ProductNotExistException, CategoryNotExistException, ManufacturerNotExistException;

    Optional<ProductDTO> deleteById(Long id) throws ProductNotExistException;
}
