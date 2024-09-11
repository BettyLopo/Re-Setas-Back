package com.resetas.resetas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.resetas.resetas.models.Category;
import com.resetas.resetas.models.Recipe;
import com.resetas.resetas.models.User;
import com.resetas.resetas.repositories.RecipeRepository;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipe() {
        return recipeRepository.findAllByOrderByIdAsc();
    }

    // public List<Recipe> getAllRecipes() {
    //     List<Recipe> recipes = recipeRepository.findAllByOrderByIdAsc();
    //     return recipes.stream().map(recipe -> {
    //         User user = recipe.getUser();
    //         Category category = recipe.getCategory();

    //         return new Recipe(
    //             recipe.getId(), 
    //             recipe.getTitle(), 
    //             recipe.getImage(), 
    //             recipe.getDuration(), 
    //             user.getUsername(), 
    //             category.getCategory()
    //         );
    //     }).collect(Collectors.toList());
    // }

    public ResponseEntity<Object> addRecipe(Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }
    
    public Optional<Recipe> findById(int id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> findAllRecipesByUser(User user) {
        return recipeRepository.findByUser(user);
    }
    
    public ResponseEntity<?> getRecipeById(int id) {
        Optional<Recipe> recipe = findById(id);
        if (recipe.isPresent()) {
            return ResponseEntity.ok(recipe.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Receta no encontrada");
        }
    }

    public ResponseEntity<Object> delete(int id) {
        Optional<Recipe> existingRecipe = findById(id);
        if (existingRecipe.isPresent()) {
            recipeRepository.delete(existingRecipe.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Object> updateRecipe(int id, Recipe recipe) {
        Optional<Recipe> existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isPresent()) {
            Recipe updateRecipe = existingRecipe.get();
            updateRecipe.setTitle(recipe.getTitle());
            updateRecipe.setImage(recipe.getImage());
            updateRecipe.setIngredients(recipe.getIngredients());
            updateRecipe.setTools(recipe.getTools());
            updateRecipe.setSteps(recipe.getSteps());
            updateRecipe.setCategory(recipe.getCategory());
            recipeRepository.save(updateRecipe);
            return new ResponseEntity<>(updateRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receta no encontrada", HttpStatus.NOT_FOUND);
        }
    }

}
