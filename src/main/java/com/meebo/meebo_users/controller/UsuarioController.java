package com.meebo.meebo_users.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meebo.meebo_users.controller.dto.user.InUserDTO;
import com.meebo.meebo_users.controller.dto.user.OutUserDTO;
import com.meebo.meebo_users.domain.entity.User;
import com.meebo.meebo_users.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsuarioController {
    private final UserService service;

    @PostMapping
    ResponseEntity<OutUserDTO> register(@RequestBody InUserDTO dto) throws URISyntaxException {
        User newUser = service.create(dto);

        URI createdUserURI = new URI("/users/" + newUser.getId());

        OutUserDTO outDTO = new OutUserDTO(newUser);

        return ResponseEntity.created(createdUserURI).body(outDTO);
    }

    @GetMapping
    ResponseEntity<List<OutUserDTO>> findAll() {
        List<User> usersFound = service.findAll();

        List<OutUserDTO> outDTOS = usersFound.stream()
                .map(OutUserDTO::new)
                .toList();

        if (usersFound.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(outDTOS);
    }

    @GetMapping("{id}")
    ResponseEntity<OutUserDTO> findById(@PathVariable UUID id) {
        User userFound = service.findById(id);

        OutUserDTO outDTO = new OutUserDTO(userFound);

        return ResponseEntity.ok(outDTO);
    }

    @PutMapping("{id}")
    ResponseEntity<OutUserDTO> update(@PathVariable UUID id, @RequestBody InUserDTO dto) {
        User userUpdated = service.update(id, dto);

        OutUserDTO outDTO = new OutUserDTO(userUpdated);

        return ResponseEntity.ok(outDTO);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}