package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.course.Courses;
import com.project.barista101.model.course.Enrollments;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollments,UUID> {
    List<Enrollments> findAllByAccount(Accounts account);
    Enrollments findByCourseAndAccount(Courses course, Accounts account);
}
