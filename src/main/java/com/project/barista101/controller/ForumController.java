package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.forum.Forums;
import com.project.barista101.payload.request.ForumRequest;
import com.project.barista101.service.ForumService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/forum")
@AllArgsConstructor
public class ForumController {
    
    @Autowired
    private final ForumService forumService;
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addForum(@RequestPart(name = "image",required = false) MultipartFile image, @RequestPart("forum") ForumRequest forumRequest){
        try{
            Forums forum = forumService.addForum(image, forumRequest);

            return ResponseHandler.generateResponse("Forum has been added successfully!", HttpStatus.OK, forum);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteForum(@RequestParam("forumId") UUID forumId){
        try{
            forumService.deleteForum(forumId);

            return ResponseHandler.generateResponse("Forum has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
    
    @GetMapping(path = "/thumbnail/{forumId}")
    public byte[] getThumbnail(@PathVariable("forumId") UUID forumId){
        return forumService.getThumbnail(forumId);
    }
}
