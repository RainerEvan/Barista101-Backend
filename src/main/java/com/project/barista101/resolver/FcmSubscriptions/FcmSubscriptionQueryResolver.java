package com.project.barista101.resolver.FcmSubscriptions;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.barista101.model.notification.FcmSubscriptions;
import com.project.barista101.service.FcmSubscriptionService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FcmSubscriptionQueryResolver implements GraphQLQueryResolver{
    
    @Autowired
    private final FcmSubscriptionService fcmSubscriptionService;

    public List<FcmSubscriptions> getAllFcmSubscriptionsForAccount(UUID accountId){
        return fcmSubscriptionService.getAllFcmSubscriptionsForAccount(accountId);
    }
}