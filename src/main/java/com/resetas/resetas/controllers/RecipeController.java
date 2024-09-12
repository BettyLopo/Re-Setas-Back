package com.resetas.resetas.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.resetas.resetas.models.Category;
import com.resetas.resetas.models.Recipe;
import com.resetas.resetas.models.User;
import com.resetas.resetas.services.CategoryService;
import com.resetas.resetas.services.RecipeService;
import com.resetas.resetas.services.UserService;

import jakarta.validation.Valid;

@RestController
public class RecipeController {
    
    private final RecipeService recipeService;
    private final UserService userService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, UserService userService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.categoryService = categoryService;
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
    @GetMapping("/recipes/user/{userId}")
    public ResponseEntity<List<Recipe>> getRecipesByUser(
        @AuthenticationPrincipal UserDetails userDetails,
        @PathVariable Integer userId) {

            Optional<User> authenticatedUserOptional = userService.findByEmail(userDetails.getUsername());
    
            if (!authenticatedUserOptional.isPresent()) {
                return ResponseEntity.status(403).build(); 
            }

            User authenticatedUser = authenticatedUserOptional.get();

            if (authenticatedUser.getId() != userId) {
                return ResponseEntity.status(403).build();
            }

            Optional<User> userOptional = userService.getUserById(userId);
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            
            List<Recipe> recipes = recipeService.findAllRecipesByUser(userOptional.get());
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
