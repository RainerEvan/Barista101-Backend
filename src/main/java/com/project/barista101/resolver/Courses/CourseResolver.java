package com.project.barista101.resolver.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Courses;
import com.project.barista101.service.ModuleService;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CourseResolver implements GraphQLResolver<Courses>{
    
    @Autowired
    private final ModuleService moduleService;

    public int getModules(Courses course){
        return moduleService.countModulesForCourse(course.getId());
    }
}
