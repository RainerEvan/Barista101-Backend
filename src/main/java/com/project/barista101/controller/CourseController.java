package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.barista101.model.course.Courses;
import com.project.barista101.payload.request.CourseRequest;
import com.project.barista101.service.CourseService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {
    
    @Autowired
    private final CourseService courseService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addCourse(@RequestPart(name = "image",required = false) MultipartFile image, @RequestPart("course") CourseRequest courseRequest){
        try{
            Courses course = courseService.addCourse(image, courseRequest);

            return ResponseHandler.generateResponse("Course has been added successfully!", HttpStatus.OK, course);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<Object> editCourse(@RequestPart(name = "image",required = false) MultipartFile image, @RequestPart("courseId") UUID courseId, @RequestPart("course") CourseRequest courseRequest){
        try{
            Courses course = courseService.editCourse(image, courseId, courseRequest);

            return ResponseHandler.generateResponse("Course has been updated successfully!", HttpStatus.OK, course);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<Object> deleteCourse(@RequestParam("courseId") UUID courseId){
        try{
            courseService.deleteCourse(courseId);

            return ResponseHandler.generateResponse("Course has been deleted successfully!", HttpStatus.OK, null);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping(path = "/thumbnail/{courseId}")
    public byte[] getThumbnail(@PathVariable("courseId") UUID courseId){
        return courseService.getThumbnail(courseId);
    }
}
