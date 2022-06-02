package com.fc.service.impl;

import com.fc.annotation.Service;
import com.fc.entity.User;
import com.fc.service.UserService;

import java.util.ArrayList;
import java.util.List;

// 注解的使用
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User login(String username, String password) {
        User user = new User();
        user.setId(1);
        user.setUsername("mock:" + username);
        user.setPassword("mock:" + password);
        return user;
    }

    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(1);
        user.setUsername("mock:易烊千玺");
        user.setPassword("mock:123456");
        return user;
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "易烊千玺", "123456"));
        users.add(new User(2, "迪丽热巴", "123456"));
        users.add(new User(3, "古力娜扎", "123456"));
        return users;
    }
}
