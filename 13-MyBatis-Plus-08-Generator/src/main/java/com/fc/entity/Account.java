package com.fc.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author BufferC
 * @since 2022-06-01
 */
@Getter
@Setter
@TableName("t_account")
@ApiModel(value = "Account对象", description = "账户表")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @TableField("t_name")
    private String name;

    @ApiModelProperty("密码")
    @TableField("t_pwd")
    private String pwd;

    @ApiModelProperty("0代表没有被删除，是可用的；1代表已经被删除，不可用")
    @TableField("availability")
    @TableLogic
    private Boolean availability;

    @ApiModelProperty("版本号，用于乐观锁")
    @TableField("version")
    @Version
    private Integer version;


}
