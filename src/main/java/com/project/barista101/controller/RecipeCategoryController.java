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

import com.project.barista101.model.recipe.RecipeCategories;
import com.project.barista101.service.RecipeCategoryService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recipe-category")
@AllArgsConstructor
public class RecipeCategoryController {
    
    @Autowired
    private final RecipeCategoryService recipeCategoryService;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addRecipeCategory(@RequestBody String name){
        try{
            RecipeCategories recipeCategory = recipeCategoryService.addRecipeCategory(name);

            return ResponseHandler.generateResponse("Recipe category has been added successfully!", HttpStatus.OK, recipeCategory);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteRecipeCategory(@RequestParam("recipeCategoryId") UUID recipeCategoryId){
        try{
            recipeCategoryService.deleteRecipeCategory(recipeCategoryId);

            return ResponseHandler.generateResponse("Recipe category has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
