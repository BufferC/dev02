package com.fc.dao.impl;

import com.fc.dao.UserDao;

public class UserDaoOracleImpl implements UserDao {
    @Override
    public void findAll() {
        System.out.println("使用Oracle数据库");
    }
}
