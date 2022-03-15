package com.fc.dao.impl;

import com.fc.dao.UserDao;
import com.fc.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        // 连接数据库....
        ArrayList<User> users = new ArrayList<>();

        users.add(new User(1, "玛卡巴卡", "123456"));
        users.add(new User(2, "汤不里不", "666666"));
        users.add(new User(3, "依古比古", "888888"));

        return users;
    }
}
