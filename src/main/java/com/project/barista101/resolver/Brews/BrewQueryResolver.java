package com.project.barista101.resolver.Brews;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.brew.Brews;
import com.project.barista101.service.BrewService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BrewQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final BrewService brewService;

    public List<Brews> getAllBrews(){
        return brewService.getAllBrews();
    }

    public Brews getBrew(UUID brewId){
        return brewService.getBrew(brewId);
    }
}
