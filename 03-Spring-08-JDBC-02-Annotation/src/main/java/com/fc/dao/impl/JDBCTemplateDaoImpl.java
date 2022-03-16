package com.fc.dao.impl;

import com.fc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCTemplateDaoImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int delete(Integer id) {
        String sql = "delete from user where id = ?";

        int affectedRows = jdbcTemplate.update(sql, id);

        return affectedRows;
    }

    public int update(User user) {
        String sql = "update user set password = ? where id = ?";

        int affectedRows = jdbcTemplate.update(sql, user.getPassword(), user.getId());

        return affectedRows;
    }

    public int insert(User user) {
        String sql = "insert into user(username, password) values(?, ?)";

        int affectedRows = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());

        return affectedRows;
    }

    // 查询总记录数
    public int findCount() {
        String sql = "select count(1) from user";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        return count;
    }

    public User findById(Integer id) {
        String sql = "select * from user where id = ?";

        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);

        return user;
    }

    public List<User> findAll() {
        String sql = "select * from user";

        // query方法需要传递两个参数
        // sql
        // BeanPropertyRowMapper的匿名对象
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }
}