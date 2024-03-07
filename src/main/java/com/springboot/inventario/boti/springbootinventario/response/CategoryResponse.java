package com.springboot.inventario.boti.springbootinventario.response;

import lombok.Data;

import java.util.List;

import com.springboot.inventario.boti.springbootinventario.models.Category;

@Data
public class CategoryResponse {

    private List<Category> category;

}
