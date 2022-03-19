package com.fc.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Teacher {
    @Value("胡图图")
    private String name;
    @Value("3")
    private Integer age;
    //@Value("#{student}")
    @Autowired
    private Student student;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", student=" + student +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
