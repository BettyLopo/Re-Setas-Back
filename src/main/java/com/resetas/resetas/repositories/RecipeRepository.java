package com.resetas.resetas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resetas.resetas.models.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllByOrderByIdAsc();
    
    
}
