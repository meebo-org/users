package com.meebo.meebo_users.controller.dto.user;

import java.util.UUID;

import com.meebo.meebo_users.domain.entity.User;

public record OutUserDTO(UUID id, String username, String email) {
    public OutUserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}