package com.resetas.resetas.services;

import java.util.List;

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

}
