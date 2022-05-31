package com.fc;

import com.fc.dao.AccountDao;
import com.fc.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OptimisticTests {
    @Autowired
    private AccountDao accountDao;

    // 测试事务优化
    @Test
    // 如果在单元测试中加上了这个注解，那么就不会直接作用于数据库
    @Transactional
    void testTransactional() {
        try {
            xinBuy();
            wifeBuy();
        } catch (Exception e) {
            System.out.println("交易失败");
        } finally {
            // 最终的结果是什么？
            Account finalResult = accountDao.selectById(1);
            System.out.println("最终的结果：" + finalResult.getMoney());
        }
    }

    // 相当于原子性的操作
    void xinBuy() {
        // 某欣查看自己的余额
        Account xinAccount = accountDao.selectById(1);
        System.out.println("某欣正在查看自己的余额：" + xinAccount.getMoney());
        xinAccount.setMoney(xinAccount.getMoney() - 3);
        int xinResult = accountDao.updateById(xinAccount);
        System.out.println(xinResult > 0 ? "购买成功，获取到了一瓶水" : "购买失败~");

    }

    void wifeBuy() {
        // 某欣的女友查看自己的余额
        Account wifeAccount = accountDao.selectById(1);
        System.out.println("某欣的老婆正在查看某欣的余额：" + wifeAccount.getMoney());
        // 某欣的老婆买了支烈艳蓝金
        wifeAccount.setMoney(wifeAccount.getMoney() - 320);
        int wifeResult = accountDao.updateById(wifeAccount);
        System.out.println(wifeResult > 0 ? "购买成功，获取到了一支口红" : "购买失败~");

    }

    @Test
    void testBuy() {
        // 某欣查看自己的余额
        Account xinAccount = accountDao.selectById(1);
        System.out.println("某欣正在查看自己的余额：" + xinAccount.getMoney());
        // 某欣的女友查看自己的余额
        Account wifeAccount = accountDao.selectById(1);
        System.out.println("某欣的老婆正在查看某欣的余额：" + wifeAccount.getMoney());
        // 某欣的老婆买了支烈艳蓝金
        wifeAccount.setMoney(wifeAccount.getMoney() - 320);
        int wifeResult = accountDao.updateById(wifeAccount);
        System.out.println(wifeResult > 0 ? "购买成功，获取到了一支口红" : "购买失败~");
        // 某欣买了瓶水
        xinAccount.setMoney(xinAccount.getMoney() - 3);
        int xinResult = accountDao.updateById(xinAccount);
        System.out.println(xinResult > 0 ? "购买成功，获取到了一瓶水" : "购买失败~");
        // 最终的结果是什么？
        Account finalResult = accountDao.selectById(1);
        System.out.println("最终的结果：" + finalResult);
    }

    @Test
    @Transactional
    void testOptimisticLockerAndRetry() {
        // 某欣查看自己的余额
        Account xinAccount = accountDao.selectById(1);
        System.out.println("某欣正在查看自己的余额：" + xinAccount.getMoney());
        // 某欣的女友查看自己的余额
        Account wifeAccount = accountDao.selectById(1);
        System.out.println("某欣的老婆正在查看某欣的余额：" + wifeAccount.getMoney());
        // 某欣的老婆买了支烈艳蓝金
        wifeAccount.setMoney(wifeAccount.getMoney() - 320);
        int wifeResult = accountDao.updateById(wifeAccount);
        System.out.println(wifeResult > 0 ? "购买成功，获取到了一支口红" : "购买失败~");
        // 某欣买了瓶水
        xinAccount.setMoney(xinAccount.getMoney() - 3);
        int xinResult = accountDao.updateById(xinAccount);

        // 重试机会只有5次
        for (int i = 0; i < 3; i++) {
            if (xinResult != 0) {
                break;
            }

            System.out.println("购买失败，有人动了余额，需要重试");

            Account newAccount = accountDao.selectById(1);
            newAccount.setMoney(newAccount.getMoney() - 3);
            xinResult = accountDao.updateById(newAccount);
        }

        if (xinResult == 0) {
            System.out.println("重试次数太多，该账号已被锁定~请稍后再试");
        }

        // 反复重试，直到正常付款为止
        //while (xinResult == 0) {
        //	System.out.println("购买失败，有人动了余额，需要重试");
        //
        //	Account newAccount = accountDao.selectById(1);
        //	newAccount.setMoney(newAccount.getMoney() - 3);
        //	xinResult = accountDao.updateById(newAccount);
        //}

        // 最终的结果是什么？
        Account finalResult = accountDao.selectById(1);
        System.out.println("最终的结果：" + finalResult);
    }
}
