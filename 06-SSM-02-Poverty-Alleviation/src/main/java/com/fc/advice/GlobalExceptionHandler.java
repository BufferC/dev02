package com.fc.advice;

import com.fc.vo.ResultVO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 用于对Controller进行增强
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public ResultVO duplicateKeyException(DuplicateKeyException e) {
        return new ResultVO(4500, "输入了重复的用户名，请换个名字", false, e.getMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResultVO missingHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ResultVO(4610, "请求方式有误，麻烦自己查看文档，谢谢！", false, e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultVO missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return new ResultVO(4600, "缺少了重要的请求参数，请重新检查后再次发送", false, e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResultVO runtimeException(RuntimeException e) {
        return new ResultVO(5000, "系统操作异常，请稍后重试或者联系管理员", false, e.getMessage());
    }
}
