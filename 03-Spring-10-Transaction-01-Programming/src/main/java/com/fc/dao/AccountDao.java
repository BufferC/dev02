package com.fc.dao;

public interface AccountDao {
    // 加钱
    void increaseMoney(Integer id, Long money);

    // 扣钱
    void decreaseMoney(Integer id, Long money);
}
