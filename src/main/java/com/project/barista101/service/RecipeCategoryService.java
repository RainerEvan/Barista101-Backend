package com.project.barista101.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.barista101.model.recipe.RecipeCategories;
import com.project.barista101.repository.RecipeCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeCategoryService {
    
    @Autowired
    private final RecipeCategoryRepository recipeCategoryRepository;

    @Transactional
    public List<RecipeCategories> getAllRecipeCategories(){
        return recipeCategoryRepository.findAll();
    }

    @Transactional
    public RecipeCategories getRecipeCategory(UUID recipeCategoryId){
        return recipeCategoryRepository.findById(recipeCategoryId)
            .orElseThrow(() -> new IllegalStateException("Recipe category with current id cannot be found"));
    }

    @Transactional
    public RecipeCategories addRecipeCategory(String name){
        RecipeCategories category = new RecipeCategories();
        category.setName(name);

        return recipeCategoryRepository.save(category);
    }

    @Transactional
    public void deleteRecipeCategory(UUID recipeCategoryId){
        RecipeCategories category = recipeCategoryRepository.findById(recipeCategoryId)
            .orElseThrow(() -> new IllegalStateException("Recipe category with current id cannot be found"));

        recipeCategoryRepository.delete(category);
    }
}
