package com.fc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
// 注意，如果我们不想使用全局的前缀，那么就可以通过这种方式去忽略掉
@TableName(value = "student", keepGlobalPrefix = false)
public class Student {
    // 因为雪花id的缘故，你们会发现很多的数据库id字段都是long类型的，刚刚好和雪花id位数匹配上
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Date birthday;
    private String info;
}
