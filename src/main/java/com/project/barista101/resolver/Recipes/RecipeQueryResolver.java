package com.project.barista101.resolver.Recipes;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.recipe.Recipes;
import com.project.barista101.service.RecipeService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecipeQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final RecipeService recipeService;

    public List<Recipes> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

    public Recipes getRecipe(UUID recipeId){
        return recipeService.getRecipe(recipeId);
    }
    
    public List<Recipes> getAllRecipesForCategory(UUID recipeCategoryId){
        return recipeService.getAllRecipesForCategory(recipeCategoryId);
    }

    public List<Recipes> getAllRecipesForAccount(UUID accountId){
        return recipeService.getAllRecipesForAccount(accountId);
    }
}
