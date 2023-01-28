package com.project.barista101.resolver.Enrollments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Enrollments;
import com.project.barista101.service.EnrollmentService;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EnrollmentResolver implements GraphQLResolver<Enrollments>{

    @Autowired
    private final EnrollmentService enrollmentService;

    public double getProgress(Enrollments enrollment){
        return enrollmentService.calculateProgress(enrollment.getId());
    }
}
