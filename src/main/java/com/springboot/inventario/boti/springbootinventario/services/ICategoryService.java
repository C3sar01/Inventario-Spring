package com.springboot.inventario.boti.springbootinventario.services;

import org.springframework.http.ResponseEntity;
import com.springboot.inventario.boti.springbootinventario.models.Category;
import com.springboot.inventario.boti.springbootinventario.response.CategoryResponseRest;

public interface ICategoryService {

    public ResponseEntity<CategoryResponseRest> search();
    public ResponseEntity<CategoryResponseRest> searchById(Long id);
    public ResponseEntity<CategoryResponseRest> save(Category category);
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);
    public ResponseEntity<CategoryResponseRest> deleteById(Long id);

}
