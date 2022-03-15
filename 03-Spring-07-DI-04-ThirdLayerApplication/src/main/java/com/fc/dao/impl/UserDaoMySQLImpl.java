package com.fc.dao.impl;

import com.fc.dao.UserDao;
import com.fc.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMySQLImpl implements UserDao {
    @Override
    public List<User> findAll() {
        // 连接数据库....
        ArrayList<User> users = new ArrayList<>();

        users.add(new User(1, "海绵宝宝", "123456"));
        users.add(new User(2, "派大星", "666666"));
        users.add(new User(3, "章鱼哥", "人生无常，大肠包小肠"));

        return users;
    }
}
