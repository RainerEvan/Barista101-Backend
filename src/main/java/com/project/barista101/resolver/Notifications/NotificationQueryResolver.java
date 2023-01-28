package com.project.barista101.resolver.Notifications;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.notification.Notifications;
import com.project.barista101.service.NotificationService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificationQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final NotificationService notificationService;

    public List<Notifications> getAllNotificationsForAccount(UUID accountId){
        return notificationService.getAllNotificationsForAccount(accountId);
    }

    public Notifications getNotification(UUID notificationId){
        return notificationService.getNotification(notificationId);
    }
}
