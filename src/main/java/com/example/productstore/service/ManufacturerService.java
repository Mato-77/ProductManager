package com.example.productstore.service;

import com.example.productstore.model.Manufacturer;
import com.example.productstore.model.dto.CategoryOrManufactureSaved;
import com.example.productstore.model.dto.ManufacturerDTO;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    List<ManufacturerDTO> findAllManufacturersWithProducts();

    Optional<CategoryOrManufactureSaved> saveManufacturer(String name);

}
