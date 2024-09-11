package com.resetas.resetas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.resetas.resetas.models.Category;
import com.resetas.resetas.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List <Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

}
