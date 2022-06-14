package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {
}
