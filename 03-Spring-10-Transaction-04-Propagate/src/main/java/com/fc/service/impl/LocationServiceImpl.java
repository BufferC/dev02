package com.fc.service.impl;

import com.fc.dao.LocationDao;
import com.fc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public void addLocation(String name) {
        locationDao.addLocation(name);
    }
}
