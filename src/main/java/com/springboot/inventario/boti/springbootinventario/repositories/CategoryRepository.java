package com.springboot.inventario.boti.springbootinventario.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.inventario.boti.springbootinventario.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {


}
