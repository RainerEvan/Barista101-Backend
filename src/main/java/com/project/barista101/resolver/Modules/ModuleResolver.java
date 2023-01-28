package com.project.barista101.resolver.Modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Contents;
import com.project.barista101.model.course.Modules;
import com.project.barista101.service.ContentService;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ModuleResolver implements GraphQLResolver<Modules>{
    @Autowired
    private final ContentService contentService;

    public List<Contents> getContents(Modules module){
        return contentService.getAllContentsForModule(module.getId());
    }
}
