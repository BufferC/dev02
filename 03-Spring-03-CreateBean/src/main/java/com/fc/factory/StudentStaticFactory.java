package com.fc.factory;

import com.fc.entity.Student;

public class StudentStaticFactory {
    public static Student getStudent() {
        return new Student();
    }
}
