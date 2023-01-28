package com.project.barista101.resolver.RecipeRatings;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.recipe.RecipeRatings;
import com.project.barista101.service.RecipeRatingService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecipeRatingQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final RecipeRatingService recipeRatingService;

    public List<RecipeRatings> getAllRatingsForRecipe(UUID recipeId){
        return recipeRatingService.getAllRatingsForRecipe(recipeId);
    }
}
