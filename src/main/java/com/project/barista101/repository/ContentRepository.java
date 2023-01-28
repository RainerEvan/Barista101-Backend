package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.course.Contents;
import com.project.barista101.model.course.Modules;

@Repository
public interface ContentRepository extends JpaRepository<Contents,UUID>{
    List<Contents> findAllByModuleOrderByCreatedAtAsc(Modules Module);
}
