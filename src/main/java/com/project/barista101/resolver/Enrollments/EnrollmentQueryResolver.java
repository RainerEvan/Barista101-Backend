package com.project.barista101.resolver.Enrollments;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Enrollments;
import com.project.barista101.service.EnrollmentService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EnrollmentQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final EnrollmentService enrollmentService;

    public List<Enrollments> getAllEnrollmentsForAccount(UUID accountId){
        return enrollmentService.getAllEnrollmentsForAccount(accountId);
    }

    public Enrollments getEnrollmentForCourseAndAccount(UUID courseId, UUID accountId){
        return enrollmentService.getEnrollmentForCourseAndAccount(courseId,accountId);
    }

    public Enrollments getEnrollment(UUID enrollmentId){
        return enrollmentService.getEnrollment(enrollmentId);
    }
}
