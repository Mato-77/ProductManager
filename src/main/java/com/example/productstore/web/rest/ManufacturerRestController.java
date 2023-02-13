package com.example.productstore.web.rest;


import com.example.productstore.model.Manufacturer;
import com.example.productstore.model.dto.CategoryOrManufactureSaved;
import com.example.productstore.model.dto.ManufacturerDTO;
import com.example.productstore.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@CrossOrigin(origins = "*",allowedHeaders = "*")
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
    @PostMapping("/add")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity<CategoryOrManufactureSaved> addManufacturer(@RequestParam String name){

        return this.manufacturerService
                .saveManufacturer(name)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
