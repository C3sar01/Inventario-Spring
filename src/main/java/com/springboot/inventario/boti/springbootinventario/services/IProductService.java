package com.springboot.inventario.boti.springbootinventario.services;

import org.springframework.http.ResponseEntity;

import com.springboot.inventario.boti.springbootinventario.models.Product;
import com.springboot.inventario.boti.springbootinventario.response.ProductResponseRest;

public interface IProductService {

    public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);
    public ResponseEntity<ProductResponseRest> searchById(Long id);
    public ResponseEntity<ProductResponseRest> searchByName(String name);
    public ResponseEntity<ProductResponseRest> deleteById(Long id);
    public ResponseEntity<ProductResponseRest> search();
    public ResponseEntity<ProductResponseRest> update(Long id,Long categoryId, Product product);
    
}
