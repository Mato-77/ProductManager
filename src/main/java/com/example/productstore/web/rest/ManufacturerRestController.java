package com.example.productstore.web.rest;


import com.example.productstore.model.Manufacturer;
import com.example.productstore.model.dto.ManufacturerDTO;
import com.example.productstore.service.ManufacturerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@CrossOrigin(origins = "http://localhost:3000")
public class ManufacturerRestController {

    private final ManufacturerService manufacturerService;

    public ManufacturerRestController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> getAllManufacturers(){
        return this.manufacturerService.findAll();
    }
    @GetMapping("/ordered")
    public List<ManufacturerDTO> getAllManufacturersOrdered(){
        return this.manufacturerService.findAllManufacturersWithProducts();
    }
}
