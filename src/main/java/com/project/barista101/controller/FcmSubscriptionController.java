package com.project.barista101.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.barista101.model.notification.FcmSubscriptions;
import com.project.barista101.payload.request.FcmSubscriptionRequest;
import com.project.barista101.service.FcmSubscriptionService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fcmsubscriptions")
@AllArgsConstructor
public class FcmSubscriptionController {
    
    @Autowired
    private final FcmSubscriptionService fcmSubscriptionService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addFcmSubscription(@RequestBody FcmSubscriptionRequest fcmSubscriptionRequest){
        try {
            FcmSubscriptions fcmSubscription = fcmSubscriptionService.addFcmSubscriptions(fcmSubscriptionRequest);
        
            return ResponseHandler.generateResponse("Fcm Subscription has been saved successfully!", HttpStatus.OK, fcmSubscription.getTimestamp());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
