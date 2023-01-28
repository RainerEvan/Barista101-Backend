package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.recipe.Recipes;
import com.project.barista101.payload.request.RecipeRequest;
import com.project.barista101.service.RecipeService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recipe")
@AllArgsConstructor
public class RecipeController {
    
    @Autowired
    private final RecipeService recipeService;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addRecipe(@RequestPart(name = "image",required = false) MultipartFile image, @RequestPart("recipe") RecipeRequest recipeRequest){
        try{
            Recipes recipe = recipeService.addRecipe(image,recipeRequest);

            return ResponseHandler.generateResponse("Recipe  has been added successfully!", HttpStatus.OK, recipe);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteRecipe(@RequestParam("recipeId") UUID recipeId){
        try{
            recipeService.deleteRecipe(recipeId);

            return ResponseHandler.generateResponse("Recipe  has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping(path = "/thumbnail/{recipeId}")
    public byte[] getThumbnail(@PathVariable("recipeId") UUID recipeId){
        return recipeService.getThumbnail(recipeId);
    }
}
