package com.project.barista101.resolver.Recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.recipe.Recipes;
import com.project.barista101.service.RecipeRatingService;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecipeResolver implements GraphQLResolver<Recipes>{
    
    @Autowired
    private final RecipeRatingService recipeRatingService;

    public double getRating(Recipes recipe){
        return recipeRatingService.calculateRatingForRecipe(recipe.getId());
    }
}
