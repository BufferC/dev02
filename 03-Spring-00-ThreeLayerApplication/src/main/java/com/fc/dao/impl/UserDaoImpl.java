package com.fc.dao.impl;

import com.fc.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void useDateBase() {
        System.out.println("连接数据库");
    }
}
