package com.fc.service;

import com.fc.vo.PageInfo;
import com.fc.entity.Student;

// 业务层接口
public interface StudentService {
    PageInfo<Student> getPageInfo(int pageNo, int pageSize);
}
