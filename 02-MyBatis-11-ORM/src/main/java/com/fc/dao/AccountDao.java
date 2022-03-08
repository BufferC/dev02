package com.fc.dao;

import com.fc.entity.Account;
import com.fc.entity.TAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountDao {
    List<Account> findAll();

    Account findById(@Param("id") Integer id);

    List<TAccount> findAllByCamelCase();
}
