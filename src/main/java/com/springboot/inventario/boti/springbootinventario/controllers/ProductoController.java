package com.springboot.inventario.boti.springbootinventario.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventario.boti.springbootinventario.dtos.ProductDTO;
import com.springboot.inventario.boti.springbootinventario.models.Product;
import com.springboot.inventario.boti.springbootinventario.repositories.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private ProductRepository productRepository;

    // Obtener productos
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        // Obtén la lista de productos del repositorio
        List<Product> products = productRepository.findAll();
        
        // Verifica si la lista de productos está vacía
        if (products.isEmpty()) {
            // Si no hay productos, devuelve un mensaje indicando que no hay stock
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay stock de productos disponible.");
        }
        
        // Si hay productos, mapea los productos a DTO y devuelve la lista
        List<ProductDTO> productDTOs = products.stream()
            .map(ProductDTO::new)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(productDTOs);
    }

    //Obtener productos por id
    @SuppressWarnings("null")
    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id){


        return productRepository.findById(id).map(ProductDTO::new).orElse(null);
    }

    //Crear producto
    @SuppressWarnings("null")
    @PostMapping("/create-products")
    public ResponseEntity<Object> newProduct(@RequestBody Product product){

        if (product.equals(null)) {
            return new ResponseEntity<>("Faltan datos", HttpStatus.FORBIDDEN);
        }

        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @SuppressWarnings("null")
    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){

        productRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("El producto con el id " + id + " ha sido eliminado");

        
    }

    
}
