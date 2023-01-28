package com.project.barista101.payload.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeCommentRequest {
    private UUID recipeId;
    private UUID accountId;
    private String body;
}
