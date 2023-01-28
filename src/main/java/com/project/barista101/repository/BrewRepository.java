package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.brew.Brews;

@Repository
public interface BrewRepository extends JpaRepository<Brews,UUID>{
    List<Brews> findAllByOrderByCreatedAtAsc();
}
