package com.project.barista101.resolver.Courses;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Courses;
import com.project.barista101.service.CourseService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CourseQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final CourseService courseService;

    public List<Courses> getAllCourses(){
        return courseService.getAllCourses();
    }

    public Courses getCourse(UUID courseId){
        return courseService.getCourse(courseId);
    }
}
