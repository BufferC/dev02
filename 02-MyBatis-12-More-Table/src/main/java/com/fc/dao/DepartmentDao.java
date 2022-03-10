package com.fc.dao;

import com.fc.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll();
}
