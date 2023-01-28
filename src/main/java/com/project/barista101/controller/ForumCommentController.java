package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.barista101.model.forum.ForumComments;
import com.project.barista101.payload.request.ForumCommentRequest;
import com.project.barista101.service.ForumCommentService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/forum-comment")
@AllArgsConstructor
public class ForumCommentController {

    @Autowired
    private final ForumCommentService forumCommentService;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addForumComment(@RequestBody ForumCommentRequest forumCommentRequest){
        try{
            ForumComments forumComment = forumCommentService.addForumComment(forumCommentRequest);

            return ResponseHandler.generateResponse("Forum comment has been added successfully!", HttpStatus.OK, forumComment.getForum().getAuthor().getId());

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteForumComment(@RequestParam("forumCommentId") UUID forumCommentId){
        try{
            forumCommentService.deleteForumComment(forumCommentId);

            return ResponseHandler.generateResponse("Forum comment has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
