package com.project.barista101.resolver.Forums;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.forum.Forums;
import com.project.barista101.service.ForumService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ForumQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final ForumService forumService;

    public List<Forums> getAllForums(){
        return forumService.getAllForums();
    }

    public Forums getForum(UUID forumId){
        return forumService.getForum(forumId);
    }
    
    public List<Forums> getAllForumsForAccount(UUID accountId){
        return forumService.getAllForumsForAccount(accountId);
    }
}
