package com.fc.dao;

import com.fc.entity.Department;
import org.apache.ibatis.annotations.Param;

public interface DepartmentDao {
    // 根据id查询当前部门以及对应的所有员工
    Department findById(@Param("id") Integer id);
}
