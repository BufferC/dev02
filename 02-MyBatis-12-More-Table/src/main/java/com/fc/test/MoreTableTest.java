package com.fc.test;

import com.fc.dao.EmployeeDao;
import com.fc.entity.Employee;
import com.fc.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class MoreTableTest {
    @Test
    public void test() {
        EmployeeDao employeeDao = MyBatisUtil.getMapper(EmployeeDao.class);

        List<Employee> employees = employeeDao.findAll();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        MyBatisUtil.commit();
    }
}
