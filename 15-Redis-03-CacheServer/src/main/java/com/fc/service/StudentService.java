package com.fc.service;

import com.fc.entity.Student;

import java.util.List;

public interface StudentService {
    // 查询全部
    List<Student> findAll();

    // 插入
    Student insert(Student student);

    Student findById(Integer id);

    Student update(Student student);
}
