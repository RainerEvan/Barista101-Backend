package com.project.barista101.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.notification.Notifications;
import com.project.barista101.payload.request.NotificationRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.FcmSubscriptionRepository;
import com.project.barista101.repository.NotificationRepository;
import com.project.barista101.utils.HeaderRequestInterceptor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    @Value("${fcm.firebaseServerKey}")
    private String firebaseServerKey;

    @Value("${fcm.firebaseApiUrl}")
    private String firebaseApiUrl;
    
    @Autowired
    private final NotificationRepository notificationRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final FcmSubscriptionRepository fcmSubscriptionRepository;

    @Transactional
    public List<Notifications> getAllNotificationsForAccount(UUID accountId){
        Accounts account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        return notificationRepository.findAllByReceiverOrderByCreatedAtDesc(account);
    }

    @Transactional
    public Notifications getNotification(UUID notificationId){
        return notificationRepository.findById(notificationId)
            .orElseThrow(() -> new IllegalStateException("Notification with current id cannot be found"));
    }

    @Transactional
    public Notifications addNotification(NotificationRequest notificationRequest){
        Accounts account = accountRepository.findById(notificationRequest.getReceiverId())
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        Notifications notification = new Notifications();
        notification.setReceiver(account);
        notification.setBody(notificationRequest.getBody());
        notification.setData(notificationRequest.getData());
        notification.setCreatedAt(OffsetDateTime.now());

        return notificationRepository.save(notification);
    }

    @Async
    @Transactional
    public String sendPushNotification(Notifications notificationObject){
        String result="";

        List<String> fcmTokens = fcmSubscriptionRepository.findAllByAccount(notificationObject.getReceiver()).stream()
            .map(subs -> subs.getToken())
            .collect(Collectors.toList());

        for(String fcmToken : fcmTokens){
            JSONObject json = new JSONObject();

            try {
                json.put("to", fcmToken);
                
                JSONObject notification = new JSONObject();
                notification.put("title", "New Notification");
                notification.put("body", notificationObject.getBody());
                notification.put("click_action", "/");

                json.put("notification", notification);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
    
            RestTemplate restTemplate = new RestTemplate();
            ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(new HeaderRequestInterceptor("Authorization", "key="+firebaseServerKey));
            interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
            restTemplate.setInterceptors(interceptors);
            HttpEntity<String> request = new HttpEntity<>(json.toString());
            result = restTemplate.postForObject(firebaseApiUrl, request, String.class);
        }
        
        return result;
    }
}
