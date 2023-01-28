package com.project.barista101.resolver.RecipeCategories;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.recipe.RecipeCategories;
import com.project.barista101.service.RecipeCategoryService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecipeCategoryQueryResolver implements GraphQLQueryResolver{

    @Autowired
    private final RecipeCategoryService recipeCategoryService;
    
    @Transactional
    public List<RecipeCategories> getAllRecipeCategories(){
        return recipeCategoryService.getAllRecipeCategories();
    }

    @Transactional
    public RecipeCategories getRecipeCategory(UUID recipeCategoryId){
        return recipeCategoryService.getRecipeCategory(recipeCategoryId);
    }
}
