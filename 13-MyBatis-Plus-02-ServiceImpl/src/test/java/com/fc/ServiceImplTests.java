package com.fc;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Student;
import com.fc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class ServiceImplTests {
    @Autowired
    private StudentService studentService;

    // 能够获取到BaseMapper的
    @Test
    void testGetBaseMapper() {
        BaseMapper<Student> baseMapper = studentService.getBaseMapper();

        System.out.println(baseMapper);

        List<Student> students = baseMapper.selectList(null);

        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    void testList() {
        List<Student> students = studentService.list();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // 删除操作是对BaseMapper的封装，原本获取到的是受影响的行数，现在变成了是否删除成功
    // getBaseMapper().deleteById(id)
    @Test
    void testRemoveById() {
        boolean isDeleted = studentService.removeById(100);
        System.out.println("是否删除成功：" + isDeleted);
    }

    // 删除多个id所对应的记录，只执行了一个SQL语句，删除了一次
    // DELETE FROM student WHERE id IN ( ? , ? , ? )
    @Test
    void testRemoveByIds() {
        boolean isDeleted = studentService.removeByIds(Arrays.asList(10, 20, 30));
        System.out.println("是否删除成功：" + isDeleted);
    }

    // 批量删除，执行了三次SQL语句，删除了三次
    // DELETE FROM student WHERE id=?
    @Test
    void testRemoveBatchByIds() {
        boolean isDeleted = studentService.removeBatchByIds(Arrays.asList(10, 20, 30));
        System.out.println("是否删除成功：" + isDeleted);
    }

    // 测试插入，和通用Mapper中的insert是一样的
    @Test
    void testSave() {
        Student student = new Student();
        student.setName("马尔扎哈");
        student.setAge(100);
        student.setBirthday(new Date());
        student.setInfo("马尔扎哈");
        boolean saved = studentService.save(student);

        System.out.println("是否插入成功：" + saved);
        System.out.println("获取主键自增长id：" + student.getId());
    }

    // 测试批量插入，执行了两次SQL
    // INSERT INTO student ( id, name, age, birthday, info ) VALUES ( ?, ?, ?, ?, ? )
    @Test
    void testSaveBatch() {
        Set<Student> students = new HashSet<>();

        Student student1 = new Student();
        student1.setName("王心凌");
        student1.setAge(40);
        student1.setBirthday(new Date());
        student1.setInfo("甜心教主");

        students.add(student1);

        Student student2 = new Student();
        student2.setName("刘耕宏");
        student2.setAge(50);
        student2.setBirthday(new Date());
        student2.setInfo("《本草纲目》");

        students.add(student2);

        boolean saved = studentService.saveBatch(students);

        System.out.println("是否插入成功：" + saved);
    }

    // 插入或者修改操作
    // 如果我们传递了id那么就是修改操作
    // SELECT id,name,age,gender,birthday,info FROM student WHERE id=?
    // UPDATE student SET name=?, age=?, gender=?, birthday=?, info=? WHERE id=?
    // 如果没有传递id就是插入操作
    // INSERT INTO student ( id, name, age, gender, birthday, info ) VALUES ( ?, ?, ?, ?, ?, ? )
    @Test
    void testSaveOrUpdate() {
        Student student = new Student();
        student.setId(552329217);
        student.setName("潘周聃");
        student.setGender("男");
        student.setAge(20);
        student.setBirthday(new Date());
        student.setInfo("修改之后的大学：郑职院");

        boolean result = studentService.saveOrUpdate(student);

        System.out.println("修改或者插入成功？" + result);
    }
}
