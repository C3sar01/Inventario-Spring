package com.springboot.inventario.boti.springbootinventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.inventario.boti.springbootinventario.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
