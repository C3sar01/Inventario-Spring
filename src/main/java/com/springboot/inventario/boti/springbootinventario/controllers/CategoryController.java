package com.springboot.inventario.boti.springbootinventario.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventario.boti.springbootinventario.models.Category;
import com.springboot.inventario.boti.springbootinventario.repositories.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    //listar categorias
     @GetMapping("/categories")
    public ResponseEntity<?> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        
        return ResponseEntity.ok(categories);
    }

    //crear categorías
    @SuppressWarnings("null")
    @PostMapping("/create-product")
    public ResponseEntity<Object> newCategory(@RequestBody Category category){

        categoryRepository.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Eliminar categorías
    @SuppressWarnings("null")
    @DeleteMapping("/delete-category")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        
        categoryRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("La categoría con el id " + id + " ha sido eliminada");


    }
    
}
