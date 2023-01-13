package com.example.productstore.service.impl;

import com.example.productstore.exception.CategoryNotExistException;
import com.example.productstore.exception.ManufacturerNotExistException;
import com.example.productstore.exception.ProductNotExistException;
import com.example.productstore.model.Category;
import com.example.productstore.model.Manufacturer;
import com.example.productstore.model.Product;
import com.example.productstore.model.dto.ProductDTO;
import com.example.productstore.model.relation.ProductCategory;
import com.example.productstore.repository.CategoryRepository;
import com.example.productstore.repository.ManufacturerRepository;
import com.example.productstore.repository.ProductCategoryRepository;
import com.example.productstore.repository.ProductRepository;
import com.example.productstore.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

     private final CategoryRepository categoryRepository;
     private final ManufacturerRepository manufacturerRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }


    @Override
    public List<ProductDTO> findAll() {

        List<Product> products =  this.productRepository.findAll();

        return products
                .stream()
                .map(product -> new ProductDTO(product,this.productCategoryRepository.findAllByProduct(product.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> findById(Long id)   {
        Product product =  this.productRepository.findById(id).orElse(new Product());

        return Optional.of(new ProductDTO(product,this.productCategoryRepository
                .findAllByProduct(id)));

    }

    @Override
    @Transactional
    public Optional<ProductDTO> saveProduct(String name, String description, Long quantity,
                                         String manufacturerName, List<String> categoriesNames)
            throws ManufacturerNotExistException, CategoryNotExistException {

        Manufacturer manufacturer = this.findManufacturerByName(manufacturerName);
        List<Category> categories = this.findCategoriesByNames(categoriesNames);

        Product product = this.productRepository.save(new Product(name,description,quantity,manufacturer,categories));

        categories
                .forEach(cat -> this.productCategoryRepository.save(new ProductCategory(cat,product)));

        return Optional.of( new ProductDTO(product,categories));

    }

    @Override
    @Transactional
    public Optional<ProductDTO> editProduct(Long id, String name, String description,
                                         Long quantity, String manufacturerName, List<String> categoriesNames)
            throws CategoryNotExistException, ManufacturerNotExistException, ProductNotExistException {

        Product product = this.findByIdOrException(id).orElseThrow(() -> new ProductNotExistException(id));
        Manufacturer manufacturer = this.findManufacturerByName(manufacturerName);
        List<Category> categories = this.findCategoriesByNames(categoriesNames);

        this.productCategoryRepository.deleteAllByProduct(product);

        product.setManufacturer(manufacturer);
        product.setName(name);
        product.setQuantity(quantity);
        product.setDescription(description);



        categories
                .forEach(cat -> this.productCategoryRepository.save(new ProductCategory(cat,product)));

       Product savedProduct =  this.productRepository.save(product);

       return Optional.of(new ProductDTO(savedProduct,categories));
    }

    @Override
    @Transactional
    public Optional<ProductDTO> deleteById(Long id) throws ProductNotExistException {
        Product product = this.findByIdOrException(id).orElseThrow(() -> new ProductNotExistException(id));
        Optional<ProductDTO> productDTO = this.findById(id);
        this.productCategoryRepository.deleteAllByProduct(product);

        this.productRepository.delete(product);
        return productDTO;
    }

    private Manufacturer findManufacturerByName(String name) throws ManufacturerNotExistException {
        return  this.manufacturerRepository.
                findById(name).orElseThrow(() -> new ManufacturerNotExistException(name));

    }
    private Category findCategoryByName(String name) throws CategoryNotExistException {
        return  this.categoryRepository.
                findById(name).orElseThrow(() -> new CategoryNotExistException(name));

    }
    private List<Category> findCategoriesByNames(List<String> names) throws CategoryNotExistException {
        List<Category> list = new ArrayList<>();
        for (String name : names) {
            Category categoryByName = findCategoryByName(name);
            list.add(categoryByName);
        }
        return list;
    }

    private Optional<Product> findByIdOrException(Long id){
        return this.productRepository.findById(id);
    }
}
