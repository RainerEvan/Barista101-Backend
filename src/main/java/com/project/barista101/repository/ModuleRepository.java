package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.course.Courses;
import com.project.barista101.model.course.Modules;

@Repository
public interface ModuleRepository extends JpaRepository<Modules,UUID> {
    List<Modules> findAllByCourseOrderByCreatedAtAsc(Courses course);
    int countByCourse(Courses course);
}
