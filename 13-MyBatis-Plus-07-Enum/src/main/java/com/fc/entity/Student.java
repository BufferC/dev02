package com.fc.entity;

import com.fc.enums.Mood;
import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private Date birthday;
    private String info;
    private Mood mood;
}
