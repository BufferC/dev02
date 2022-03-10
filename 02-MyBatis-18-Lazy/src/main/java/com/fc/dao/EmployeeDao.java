package com.fc.dao;

import com.fc.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    // 根据部门id查询指定部门下的所有员工
    List<Employee> findById(@Param("id") Integer id);
}
