package com.fc.entity;

import lombok.Data;

@Data
public class Employee {
    private Integer id;
    private String name;
    // 一对一，一个员工对应一个部门
    private Department department;
}
