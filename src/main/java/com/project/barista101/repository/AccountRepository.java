package com.project.barista101.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.barista101.model.account.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,UUID>{
    Optional<Accounts> findByUsername(String username);
    Optional<Accounts> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
