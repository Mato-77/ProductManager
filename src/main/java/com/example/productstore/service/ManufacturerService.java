package com.example.productstore.service;

import com.example.productstore.model.Manufacturer;
import com.example.productstore.model.dto.ManufacturerDTO;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> findAll();

    List<ManufacturerDTO> findAllManufacturersWithProducts();

}
