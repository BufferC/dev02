package com.fc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生表
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student extends Model<Student> implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 
     */
    @TableField(value = "birthday")
    private Date birthday;

    /**
     * 
     */
    @TableField(value = "info")
    private String info;

    /**
     * 
     */
    @TableField(value = "mood")
    private Object mood;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}