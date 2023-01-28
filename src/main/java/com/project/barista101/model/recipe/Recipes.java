package com.project.barista101.model.recipe;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.project.barista101.model.account.Accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipes")
public class Recipes {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "recipe_category_id")
    private RecipeCategories category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts author;

    private String title;
    
    @Lob
    private String description;

    @Lob
    private String equipments;

    @Lob
    private String ingredients;

    @Lob
    private String instructions;

    @Lob
    private String notes;

    @Lob
    private String thumbnail;

    private OffsetDateTime createdAt;

}
