package com.fc.service.impl;

import com.fc.dao.LocationDao;
import com.fc.service.LocationService;
import com.fc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationDao locationDao;

    @Override
    public List<UserVO> findAll() {
        return locationDao.findAll();
    }
}
