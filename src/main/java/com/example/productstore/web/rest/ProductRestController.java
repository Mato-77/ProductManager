package com.example.productstore.web.rest;

import com.example.productstore.exception.CategoryNotExistException;
import com.example.productstore.exception.ManufacturerNotExistException;
import com.example.productstore.exception.ProductNotExistException;
import com.example.productstore.model.Category;
import com.example.productstore.model.dto.ProductDTO;
import com.example.productstore.model.dto.ProductSavedDTO;
import com.example.productstore.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductDTO> getProducts(){
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) throws ProductNotExistException {
        return
                this.productService.findById(id)
                        .map(product -> ResponseEntity.ok().body(product))
                        .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/save")
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductSavedDTO productSavedDTO

                                                  ) throws  ManufacturerNotExistException, CategoryNotExistException {
        return
                this.productService.saveProduct(productSavedDTO.getName()
                                , productSavedDTO.getDescription(),
                                productSavedDTO.getQuantity(), productSavedDTO.getManufacturerName(),
                                productSavedDTO.getCategoriesNames().stream()
                                        .map(Category::getName).collect(Collectors.toList()))

                        .map(product -> ResponseEntity.ok().body(product))
                        .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable Long id,
                                               @RequestBody ProductSavedDTO productDTO


    ) throws ProductNotExistException, ManufacturerNotExistException, CategoryNotExistException {
        return
                this.productService.editProduct(id,productDTO.getName(), productDTO.getDescription()
                                , productDTO.getQuantity(), productDTO.getManufacturerName(),productDTO.getCategoriesNames().stream()
                        .map(Category::getName).collect(Collectors.toList()))
                        .map(product -> ResponseEntity.ok().body(product))
                        .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) throws ProductNotExistException {

      Optional<ProductDTO> productDTO = this.productService.deleteById(id);
       return productDTO.map(pr -> ResponseEntity.ok().body(pr))
               .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
