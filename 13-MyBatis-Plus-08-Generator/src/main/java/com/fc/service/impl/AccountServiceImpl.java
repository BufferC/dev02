package com.fc.service.impl;

import com.fc.entity.Account;
import com.fc.dao.AccountMapper;
import com.fc.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author BufferC
 * @since 2022-06-01
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
