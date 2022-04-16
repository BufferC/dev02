package com.fc.controller;

import com.fc.service.FileUploadService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("uploadImg")
    public ResultVO fileUpload(MultipartFile file, @RequestParam String type) {
        return fileUploadService.fileUpload(file, type);
    }
}
