package com.resetas.resetas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resetas.resetas.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();
    
} 
