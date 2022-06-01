package com.fc.dao;

import com.fc.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author BufferC
 * @since 2022-06-01
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
