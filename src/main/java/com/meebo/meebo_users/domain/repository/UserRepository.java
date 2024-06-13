package com.meebo.meebo_users.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meebo.meebo_users.domain.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    
}
