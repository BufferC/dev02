package com.fc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
// 用于关联实体类和数据库表的
@TableName(value = "t_account", schema = "mybatis_plus")
public class Account {
    // 用于关联我们的主键id字段
    @TableId(value = "t_id")
    private Integer id;
    private String tName;
    private String tPwd;
}
