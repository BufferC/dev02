package com.fc.test;

import com.fc.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ARTests {
    @Test
    void testInsert() {
        Student student = new Student();

        student.setName("AR模式");
        student.setInfo("测试AR模式插入");

        boolean result = student.insert();

        System.out.println("是否插入成功:" + result);
    }

    @Test
    void testUpdate() {
        Student student = new Student();
        student.setId(1531890273551642626L);
        student.setAge(30);
        student.setGender("男");
        student.setInfo("测试AR模式修改");

        boolean result = student.updateById();

        System.out.println("是否修改成功:" + result);
    }

    @Test
    void testSelect() {
        Student student = new Student();

        List<Student> students = student.selectAll();

        students.forEach(System.out::println);
    }

    @Test
    void testDelete() {
        Student student = new Student();

        student.setId(1531890273551642626L);

        boolean result = student.deleteById();

        System.out.println("是否删除成功:" + result);
    }
}
