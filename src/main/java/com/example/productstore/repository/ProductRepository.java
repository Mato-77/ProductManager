package com.example.productstore.repository;

import com.example.productstore.model.Product;
import com.example.productstore.model.dto.ManufacturerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    @Query(value = "select new com.example.productstore.model.dto.ManufacturerDTO( m.name,count(p.id) ) from Manufacturer m" +
            " left join Product p on p.manufacturer.name = m.name " +
            "group by m.name " +
            "order by count (p.id) desc ")
    List<ManufacturerDTO> findAllByManufacturer();
}
