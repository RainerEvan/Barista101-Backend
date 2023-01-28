package com.project.barista101.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.barista101.model.notification.Notifications;
import com.project.barista101.payload.request.NotificationRequest;
import com.project.barista101.service.NotificationService;
import com.project.barista101.utils.ResponseHandler;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/notification")
@AllArgsConstructor
public class NotificationController {
    
    @Autowired
    private final NotificationService notificationService;

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addNotification(@RequestBody NotificationRequest notificationRequest){
        try{
            Notifications notification = notificationService.addNotification(notificationRequest);

            String push = notificationService.sendPushNotification(notification);

            return ResponseHandler.generateResponse("Notification has been added successfully!", HttpStatus.OK, notification + push);

        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

}
