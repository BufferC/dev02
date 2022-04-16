package com.fc.service.impl;

import com.fc.service.FileUploadService;
import com.fc.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public ResultVO fileUpload(MultipartFile file, String type) {
        // 准备文件存储的路径
        String path = "D:\\server\\apache-tomcat-8.5.37\\webapps\\upload\\Poverty-Alleviation\\" + type;

        File pathFile = new File(path);

        // 如果文件夹路径不存在
        if (!pathFile.exists()) {
            // 创建多级路径
            pathFile.mkdirs();
        }

        // 获取原始的文件名
        String originalFilename = file.getOriginalFilename();

        // 获取日期时间格式化器
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        // 获取格式化之后的当前日期时间
        String formatDate = formatter.format(new Date());

        // 获取文件后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));

        // 新的文件名等于格式化之后的日期时间加上后缀名
        originalFilename = formatDate + suffix;

        ResultVO resultVO = new ResultVO();

        try {
            // 文件上传操作
            file.transferTo(new File(pathFile, originalFilename));

            // 准备一个map用于存储图片的网络路径
            HashMap<String, Object> map = new HashMap<>();
            map.put("imgurl", "http://localhost:8081/upload/Poverty-Alleviation/" + type + "/" + originalFilename);

            resultVO.setCode(200);
            resultVO.setSuccess(true);
            resultVO.setMessage("上传成功！！！");
            resultVO.setData(map);
        } catch (IOException e) {
            e.printStackTrace();
            resultVO.setCode(-1000);
            resultVO.setSuccess(false);
            resultVO.setMessage("上传失败！！！");
        }

        return resultVO;
    }
}
