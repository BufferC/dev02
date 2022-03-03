package com.fc.service.impl;

import com.fc.dao.StudentDao;
import com.fc.dao.impl.StudentDaoImpl;
import com.fc.vo.PageInfo;
import com.fc.entity.Student;
import com.fc.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    // 业务逻辑层依赖数据访问层
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public PageInfo<Student> getPageInfo(int pageNo, int pageSize) {
        int totalCount = studentDao.getTotalCount();
        List<Student> list = studentDao.getList(pageNo, pageSize);

        return new PageInfo<>(totalCount, pageSize, pageNo, list);
    }
}
