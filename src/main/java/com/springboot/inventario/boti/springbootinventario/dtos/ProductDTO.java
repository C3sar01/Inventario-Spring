package com.springboot.inventario.boti.springbootinventario.dtos;

import java.math.BigDecimal;

import com.springboot.inventario.boti.springbootinventario.models.Category;
import com.springboot.inventario.boti.springbootinventario.models.Product;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer stock;
    private Category category; // Se usa el ID de la categoría en lugar de la categoría completa

    // Constructor vacío
    public ProductDTO() {
    }

    // Constructor con todos los campos
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.category = product.getCategory();
    }


}
