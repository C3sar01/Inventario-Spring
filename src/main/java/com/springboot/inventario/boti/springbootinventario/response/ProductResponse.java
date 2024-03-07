package com.springboot.inventario.boti.springbootinventario.response;

import lombok.Data;

import java.util.List;

import com.springboot.inventario.boti.springbootinventario.models.Product;

@Data
public class ProductResponse {

    List<Product> products;

}
