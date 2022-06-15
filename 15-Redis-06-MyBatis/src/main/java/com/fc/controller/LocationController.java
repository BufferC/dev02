package com.fc.controller;

import com.fc.service.LocationService;
import com.fc.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping("findAll")
    public List<UserVO> findAll() {
        return locationService.findAll();
    }
}
