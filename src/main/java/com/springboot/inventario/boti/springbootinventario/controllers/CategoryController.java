package com.springboot.inventario.boti.springbootinventario.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventario.boti.springbootinventario.models.Category;
import com.springboot.inventario.boti.springbootinventario.response.CategoryResponseRest;
import com.springboot.inventario.boti.springbootinventario.services.ICategoryService;
import com.springboot.inventario.boti.springbootinventario.util.CategoryExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    // Listar categorias
    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> getCategories() {

        ResponseEntity<CategoryResponseRest> response = categoryService.search();
        return response;
        
    }

    // Listar categorias por id
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoriesById(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = categoryService.searchById(id);
        return response;
    }

    // Crear categorías
    @PostMapping("/create-categories")
    public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {

        ResponseEntity<CategoryResponseRest> response = categoryService.save(category);
        return response;
    }

    //Actualizar categorias
    @PutMapping("/update-categories/{id}")
    public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = categoryService.update(category, id);
        return response;
    }

    // Eliminar categorías
    @DeleteMapping("/delete-categories/{id}")
    public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {

        ResponseEntity<CategoryResponseRest> response = categoryService.deleteById(id);
        return response;
    }

    //Exportar a excel
    @GetMapping("/categories/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=categorias.xlsx";
        response.setHeader(headerKey, headerValue);

        ResponseEntity<CategoryResponseRest> categoryResponse = categoryService.search();
        CategoryExcelExporter excelExporter = new CategoryExcelExporter(categoryResponse.getBody().getCategoryResponse().getCategory());
        excelExporter.export(response);

    }
}

