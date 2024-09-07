package com.resetas.resetas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.resetas.resetas.models.Recipe;
import com.resetas.resetas.services.RecipeService;

import jakarta.validation.Valid;

@RestController
public class RecipeController {
    
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipe() {
        List<Recipe> recipes = recipeService.getRecipe();
        if (recipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(recipes);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @PostMapping("/recipes/create")
    public ResponseEntity<Object> addRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }


    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("/recipes/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable int id) {
        return recipeService.getRecipeById(id);
    }

    @CrossOrigin(origins = "http://localhost:3001")
    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
        return recipeService.delete(id);
    }
    
    @CrossOrigin(origins = "http://localhost:3001")
    @PutMapping("/recipes/{id}")
    public ResponseEntity<Object> updateRecipe(@Valid @PathVariable("id") int id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

}
