package com.project.barista101.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.account.Accounts;
import com.project.barista101.model.forum.Forums;

@Repository
public interface ForumRepository extends JpaRepository<Forums,UUID>{
    List<Forums> findAllByOrderByCreatedAtDesc();
    List<Forums> findAllByAuthorOrderByCreatedAtDesc(Accounts account);
}
