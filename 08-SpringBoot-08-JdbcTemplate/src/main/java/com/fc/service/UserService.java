package com.fc.service;

import com.fc.entity.User;

import java.util.List;

public interface UserService {
    // 查询全部
    List<User> findAll();
    // 根据id查询
    User findById(Integer id);
    // 查询总记录数
    Integer findCount();
    // 添加学生
    int insert(User user);
    // 修改学生
    int update(User user);
    // 删除学生
    int delete(Integer id);
}
