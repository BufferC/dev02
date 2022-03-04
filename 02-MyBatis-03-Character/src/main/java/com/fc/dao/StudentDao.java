package com.fc.dao;

import com.fc.entity.Student;

import java.util.List;

public interface StudentDao {
    // 查询小于指定年龄的学生
    List<Student> findByLessThanAge(Integer age);

    // 查询大于指定年龄的学生
    List<Student> findGreaterThanAge(Integer age);
}
