package com.kosh.jwtapp.service;

import com.kosh.jwtapp.model.User;

import java.util.List;

public interface UserService {
    User register (User user);
    List<User> getAll();
    User findByUsername(String username);
    User findById (Long id);
    void deleteUser (Long id);
}
