package com.fc.service.impl;

import com.fc.dao.AccountDao;
import com.fc.service.AccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class ProgrammingServiceImpl implements AccountService {
    private AccountDao accountDao;

    private TransactionTemplate transactionTemplate;

    @Override
    public void transfer(Integer from, Integer to, Long money) {
        // 开启一个事务
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                accountDao.decreaseMoney(from, money);

                int num = 1 / 0;

                accountDao.increaseMoney(to, money);
            }
        });
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
