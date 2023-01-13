package com.example.productstore.repository;

import com.example.productstore.model.Category;
import com.example.productstore.model.Product;
import com.example.productstore.model.dto.CategoryDTO;
import com.example.productstore.model.relation.ProductCategory;
import com.example.productstore.model.relation.ProductCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,ProductCategoryPK> {

     void deleteAllByCategory(Category category);

     void deleteAllByProduct(Product product);

     @Query(value = "select new com.example.productstore.model.dto.CategoryDTO(c.name, count (pc.id.productId)) from Category c " +
             "left join ProductCategory pc on c.name = pc.id.category " +
             "group by c.name" +
             " order by count (pc.id.productId) desc ")
     List<CategoryDTO> findAllByGroupedCategories();

     @Query(value = "select c.name from ProductCategory pc" +
             " join Category c on c.name = pc.id.category " +
             " where pc.id.productId = :product_id" )
     List<Category> findAllByProduct(@Param(value = "product_id") Long id);

    @Query(value = "select p from ProductCategory pc" +
            " join Product p on p.id = pc.id.productId " +
            " where pc.id.category = :category_name" )
    List<Product> findAllByCategory(@Param(value = "category_name") String name);
}
