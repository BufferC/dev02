package com.fc.dao.impl;

import com.fc.dao.UserDao;

public class UserDaoMySQLImpl implements UserDao {
    @Override
    public void useDateBase() {
        System.out.println("使用MySQL连接数据库");
    }
}
