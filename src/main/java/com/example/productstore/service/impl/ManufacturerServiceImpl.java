package com.example.productstore.service.impl;

import com.example.productstore.model.Manufacturer;
import com.example.productstore.model.dto.ManufacturerDTO;
import com.example.productstore.repository.ManufacturerRepository;
import com.example.productstore.repository.ProductRepository;
import com.example.productstore.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;


    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ProductRepository productRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public List<ManufacturerDTO> findAllManufacturersWithProducts() {
        return this.productRepository.findAllByManufacturer();
    }
}
