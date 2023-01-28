package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.recipe.RecipeCategories;
import com.project.barista101.model.recipe.Recipes;

@Repository
public interface RecipeRepository extends JpaRepository<Recipes,UUID>{
    List<Recipes> findAllByOrderByCreatedAtDesc();
    List<Recipes> findAllByAuthorOrderByCreatedAtDesc(Accounts account);
    List<Recipes> findAllByCategoryOrderByCreatedAtDesc(RecipeCategories category);
}
