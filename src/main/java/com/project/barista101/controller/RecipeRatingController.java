package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.barista101.model.recipe.RecipeRatings;
import com.project.barista101.payload.request.RecipeRatingRequest;
import com.project.barista101.service.RecipeRatingService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recipe-rating")
@AllArgsConstructor
public class RecipeRatingController {
    @Autowired
    private final RecipeRatingService recipeRatingService;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addRecipeRating(@RequestBody RecipeRatingRequest recipeRatingRequest){
        try{
            RecipeRatings recipeRating = recipeRatingService.addRecipeRating(recipeRatingRequest);

            return ResponseHandler.generateResponse("Recipe rating has been added successfully!", HttpStatus.OK, recipeRating.getRecipe().getAuthor().getId());

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteRecipeRating(@RequestParam("recipeRatingId") UUID recipeRatingId){
        try{
            recipeRatingService.deleteRecipeRating(recipeRatingId);

            return ResponseHandler.generateResponse("Recipe rating has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
