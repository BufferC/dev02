package com.fc.service.impl;

import com.fc.dao.UserDao;
import com.fc.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public void useDateBase() {
        userDao.useDateBase();
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
