package com.fc.service;

import com.fc.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Integer id);

    int insert(User user);

    int update(User user);
}
