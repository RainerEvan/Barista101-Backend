package com.project.barista101.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.recipe.RecipeRatings;
import com.project.barista101.model.recipe.Recipes;
import com.project.barista101.payload.request.RecipeRatingRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.RecipeRatingRepository;
import com.project.barista101.repository.RecipeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeRatingService {
    
    @Autowired
    private final RecipeRatingRepository recipeRatingRepository;
    @Autowired
    private final RecipeRepository recipeRepository;
    @Autowired
    private final AccountRepository accountRepository;

    @Transactional
    public List<RecipeRatings> getAllRatingsForRecipe(UUID recipeId){
        Recipes recipe = recipeRepository.findById(recipeId)
            .orElseThrow(() -> new IllegalStateException("Recipe with current id cannot be found"));

        return recipeRatingRepository.findAllByRecipeOrderByCreatedAtDesc(recipe);
    }

    @Transactional
    public RecipeRatings addRecipeRating(RecipeRatingRequest recipeRatingRequest){
        Recipes recipe = recipeRepository.findById(recipeRatingRequest.getRecipeId())
            .orElseThrow(() -> new IllegalStateException("Recipe with current id cannot be found"));

        Accounts account = accountRepository.findById(recipeRatingRequest.getAccountId())
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        RecipeRatings recipeRating = new RecipeRatings();
        recipeRating.setRecipe(recipe);
        recipeRating.setAuthor(account);
        recipeRating.setRating(recipeRatingRequest.getRating());
        recipeRating.setBody(recipeRatingRequest.getBody());
        recipeRating.setCreatedAt(OffsetDateTime.now());

        return recipeRatingRepository.save(recipeRating);
    }

    @Transactional
    public void deleteRecipeRating(UUID recipeRatingId){
        RecipeRatings recipeRating = recipeRatingRepository.findById(recipeRatingId)
            .orElseThrow(() -> new IllegalStateException("Recipe rating with current id cannot be found"));

        recipeRatingRepository.delete(recipeRating);
    }

    @Transactional
    public double calculateRatingForRecipe(UUID recipeId){
        List<RecipeRatings> recipeRatings = getAllRatingsForRecipe(recipeId);

        double totalRating = 0;

        for(RecipeRatings recipeRating:recipeRatings){
            double rate = recipeRating.getRating();

            totalRating += rate;
        }

        double percentage = 0;

        if(totalRating != 0){
            percentage = totalRating/recipeRatings.size();
        }

        BigDecimal rating = new BigDecimal(Double.toString(percentage));
        rating = rating.setScale(1, RoundingMode.HALF_UP);

        return rating.doubleValue();
    }
}
