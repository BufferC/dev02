package com.fc.factory;

import com.fc.entity.Student;

public class StudentFactory {
    public Student getStudent() {
        return new Student();
    }
}
