package com.project.barista101.resolver.Modules;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Modules;
import com.project.barista101.service.ModuleService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ModuleQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final ModuleService moduleService;

    public List<Modules> getAllModulesForCourse(UUID courseId){
        return moduleService.getAllModulesForCourse(courseId);
    }

    public Modules getModule(UUID moduleId){
        return moduleService.getModule(moduleId);
    }
}
