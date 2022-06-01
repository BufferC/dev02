package com.fc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 账户表
 * @TableName t_account
 */
@TableName(value ="t_account")
@Data
public class Account implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 0代表没有被删除，是可用的；1代表已经被删除，不可用
     */
    private Integer availability;

    /**
     * 版本号，用于乐观锁
     */
    private Integer version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}