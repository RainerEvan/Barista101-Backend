package com.project.barista101.resolver.ForumComments;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.forum.ForumComments;
import com.project.barista101.service.ForumCommentService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ForumCommentQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final ForumCommentService forumCommentService;

    public List<ForumComments> getAllCommentsForForum(UUID forumId){
        return forumCommentService.getAllCommentsForForum(forumId);
    }
}
