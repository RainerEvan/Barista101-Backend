package com.project.barista101.resolver.Contents;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.course.Contents;
import com.project.barista101.service.ContentService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ContentQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final ContentService contentService;

    public List<Contents> getAllContentsForModule(UUID moduleId){
        return contentService.getAllContentsForModule(moduleId);
    }

    public Contents getContent(UUID contentId){
        return contentService.getContent(contentId);
    }
}
