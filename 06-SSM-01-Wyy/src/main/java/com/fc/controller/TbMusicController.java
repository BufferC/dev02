package com.fc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("music")
public class TbMusicController {
    @RequestMapping("findAll")
    public String findAll() {
        return "真香";
    }
}
