package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.notification.FcmSubscriptions;

@Repository
public interface FcmSubscriptionRepository extends JpaRepository<FcmSubscriptions,UUID>{
    List<FcmSubscriptions> findAllByAccount(Accounts account);
}
