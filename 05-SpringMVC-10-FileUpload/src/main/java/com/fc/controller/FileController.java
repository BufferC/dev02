package com.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileController {
    @RequestMapping("upload")
    public ModelAndView upload(MultipartFile upload, ModelAndView mv) {
        // 这个路径必须使用本地路径
        // 不能使用网络路径
        String path = "D:/server/apache-tomcat-8.5.37/webapps/upload";

        // 创建文件对象
        File file = new File(path);

        // 如果路径不存在
        if (!file.exists()) {
            // 创建多级路径
            file.mkdirs();
        }

        // 获取文件名
        String filename = upload.getOriginalFilename();

        try {
            if (filename != null) {
                // 上传
                upload.transferTo(new File(path, filename));

                mv.addObject("img", filename);

                mv.setViewName("/success.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mv;
    }
}
