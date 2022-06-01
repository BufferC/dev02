package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.entity.Account;
import com.fc.service.AccountService;
import com.fc.dao.AccountMapper;
import org.springframework.stereotype.Service;

/**
* @author Buffer
* @description 针对表【t_account(账户表)】的数据库操作Service实现
* @createDate 2022-06-01 15:41:52
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{

}




