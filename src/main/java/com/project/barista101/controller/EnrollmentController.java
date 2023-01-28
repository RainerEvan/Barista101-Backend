package com.project.barista101.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.project.barista101.model.course.Enrollments;
import com.project.barista101.payload.request.EnrollmentRequest;
import com.project.barista101.service.EnrollmentService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/enrollment")
@AllArgsConstructor
public class EnrollmentController {
    
    @Autowired
    private final EnrollmentService enrollmentService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addEnrollment(@RequestBody EnrollmentRequest enrollmentRequest){
        try{
            Enrollments enrollment = enrollmentService.addEnrollment(enrollmentRequest);

            return ResponseHandler.generateResponse("Enrollment has been added successfully!", HttpStatus.OK, enrollment.getId());

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping(path = "/finish-module")
    public ResponseEntity<Object> finishModule(@RequestPart("enrollmentId") UUID enrollmentId, @RequestPart("moduleId") UUID moduleId){
        try{
            Enrollments enrollment = enrollmentService.finishModule(enrollmentId, moduleId);

            return ResponseHandler.generateResponse("Enrollment has been updated successfully!", HttpStatus.OK, enrollment);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

}
