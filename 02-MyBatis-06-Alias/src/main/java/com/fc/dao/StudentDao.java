package com.fc.dao;

import com.fc.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student findById(Integer id);
    List<Student> findByName(String name);
    List<Student> findByAge(Byte age);
    List<Student> findByGender(String gender);
}
