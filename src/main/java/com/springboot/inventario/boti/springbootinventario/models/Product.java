package com.springboot.inventario.boti.springbootinventario.models;

import java.math.BigDecimal;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    
    private Long id;
    private String name;
    private BigDecimal  price;
    private String description;
    private Integer stock;

    @ManyToOne
    private Category category;


    
}
