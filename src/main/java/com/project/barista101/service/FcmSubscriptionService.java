package com.project.barista101.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.notification.FcmSubscriptions;
import com.project.barista101.payload.request.FcmSubscriptionRequest;
import com.project.barista101.repository.AccountRepository;
import com.project.barista101.repository.FcmSubscriptionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FcmSubscriptionService {
    
    @Autowired
    private final FcmSubscriptionRepository fcmSubscriptionRepository;
    @Autowired
    private final AccountRepository accountRepository;

    @Transactional
    public List<FcmSubscriptions> getAllFcmSubscriptionsForAccount(UUID accountId){
        Accounts account = accountRepository.findById(accountId)
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        return fcmSubscriptionRepository.findAllByAccount(account);
    }

    @Transactional
    public FcmSubscriptions addFcmSubscriptions(FcmSubscriptionRequest fcmSubscriptionRequest){
        Accounts account = accountRepository.findById(fcmSubscriptionRequest.getAccountId())
            .orElseThrow(() -> new IllegalStateException("Account with current id cannot be found"));

        FcmSubscriptions fcmSubscription = new FcmSubscriptions();
        fcmSubscription.setAccount(account);
        fcmSubscription.setToken(fcmSubscriptionRequest.getToken());
        fcmSubscription.setTimestamp(OffsetDateTime.now());

        return fcmSubscriptionRepository.save(fcmSubscription);
    }
}
