package com.springboot.inventario.boti.springbootinventario.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventario.boti.springbootinventario.repositories.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductRepository productRepository;

    //Obtener productos
    
}
