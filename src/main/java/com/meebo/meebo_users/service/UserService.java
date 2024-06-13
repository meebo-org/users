package com.meebo.meebo_users.service;

import java.util.UUID;

import com.meebo.meebo_users.controller.dto.user.InUserDTO;
import com.meebo.meebo_users.domain.entity.User;

public interface UserService extends CrudService<User, UUID, InUserDTO> {
    
}