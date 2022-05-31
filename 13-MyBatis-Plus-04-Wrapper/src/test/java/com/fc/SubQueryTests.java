package com.fc;

import com.fc.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 测试子查询相关的
@SpringBootTest
public class SubQueryTests {
    @Autowired
    private StudentDao studentDao;

    // 单行单列
    @Test
    void testSpecificField() {

    }
}
