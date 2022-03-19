package com.fc.service.impl;

import com.fc.dao.AccountDao;
import com.fc.service.AccountService;
import com.fc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private LocationService locationService;

    @Override
    public void addAccount(String name, String location) {

        accountDao.addAccount(name);

        int num = 1 / 0;

        locationService.addLocation(location);
    }
}
