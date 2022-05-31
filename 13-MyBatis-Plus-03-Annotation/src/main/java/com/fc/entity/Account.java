package com.fc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
// 用于关联实体类和数据库表的
@TableName(value = "t_account", schema = "mybatis_plus")
public class Account {
    // 用于关联我们的主键id字段
    @TableId(value = "t_id")
    private Integer id;
    @TableField(value = "t_name")
    private String username;
    @TableField(value = "t_pwd")
    private String password;
    @TableLogic
    private Boolean availability;
    //@TableField(exist = false)
    //private Double money;
}
