package com.meebo.meebo_users.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.meebo.meebo_users.controller.dto.user.InUserDTO;
import com.meebo.meebo_users.domain.entity.User;
import com.meebo.meebo_users.domain.repository.UserRepository;
import com.meebo.meebo_users.service.UserService;
import com.meebo.meebo_users.service.exception.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User create(InUserDTO dto) {
        User newUser = new User();

        BeanUtils.copyProperties(dto, newUser);

        return repository.save(newUser);
    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(UUID id, InUserDTO dto) {
        User userFound = repository.findById(id).orElseThrow(NotFoundException::new);

        BeanUtils.copyProperties(dto, userFound);

        return repository.save(userFound);
    }

    @Override
    public void delete(UUID id) {
        User userFound = repository.findById(id).orElseThrow(NotFoundException::new);

        repository.delete(userFound);
    }
}
