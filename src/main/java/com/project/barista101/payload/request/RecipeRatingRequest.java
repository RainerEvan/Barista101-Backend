package com.project.barista101.payload.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeRatingRequest {
    private UUID recipeId;
    private UUID accountId;
    private Integer rating;
    private String body;
}
