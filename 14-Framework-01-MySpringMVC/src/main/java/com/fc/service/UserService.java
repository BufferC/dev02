package com.fc.service;

import com.fc.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);

    User findById(Integer id);

    List<User> findAll();
}
