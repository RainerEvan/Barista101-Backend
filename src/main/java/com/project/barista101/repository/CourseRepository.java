package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.course.Courses;

@Repository
public interface CourseRepository extends JpaRepository<Courses,UUID> {
    List<Courses> findAllByOrderByCreatedAtAsc();
}
