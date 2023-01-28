package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.notification.Notifications;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications,UUID>{
    List<Notifications> findAllByReceiverOrderByCreatedAtDesc(Accounts receiver);
}
