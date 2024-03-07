package com.springboot.inventario.boti.springbootinventario.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.inventario.boti.springbootinventario.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

}
