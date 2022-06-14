package com.fc.controller;

import com.fc.entity.Student;
import com.fc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("findAll")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @RequestMapping("findById")
    public Student findById(Integer id) {
        return studentService.findById(id);
    }

    @RequestMapping("update")
    public Student update(Student student) {
        return studentService.update(student);
    }

    @RequestMapping("insert")
    public Student insert(Student student) {
        return studentService.insert(student);
    }
}
