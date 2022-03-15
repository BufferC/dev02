package com.fc.service.impl;

import com.fc.dao.UserDao;
import com.fc.entity.User;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    // 根据数据类型进行注入
    @Autowired
    // 在数据类型相同的情况下，再根据id进行注入
    @Qualifier("userDaoMySQLImpl")
    // 既根据类型又会匹配id
    //@Resource(name = "userDaoMySQLImpl")
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
