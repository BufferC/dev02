package com.fc.dao;

import com.fc.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    // 模糊查询
    List<Student> findByKeyword(@Param("name") String name, @Param("age") Integer age);

    // 测试where
    List<Student> findByStudent(Student student);

    // 测试trim
    List<Student> findByStudentWithTrim(Student student);

    int update(Student student);

    int updateWithTrim(Student student);

    // 可变长参数或者不定长参数，特点是参数的个数可以是0个、1个或者多个。底层是一个数组
    // 可变长参数是JDK1.5的新特性，只能用在方法声明参数列表中的最后一个
    int deleteMore(Integer... ids);
}
