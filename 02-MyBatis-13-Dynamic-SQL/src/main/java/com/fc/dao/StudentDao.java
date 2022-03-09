package com.fc.dao;

import com.fc.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    // 模糊查询
    List<Student> findByKeyword(@Param("name") String name, @Param("age") Integer age);

    List<Student> findByStudent(Student student);

    int update(Student student);
}
