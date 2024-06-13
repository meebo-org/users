package com.meebo.meebo_users.service;

import java.util.List;

public interface CrudService<T, ID, DTO> {
    T create(DTO dto);

    T findById(ID id);

    List<T> findAll();

    T update(ID id, DTO dto);

    void delete(ID id);
}