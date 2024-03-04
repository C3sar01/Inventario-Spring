package com.springboot.inventario.boti.springbootinventario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.inventario.boti.springbootinventario.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
