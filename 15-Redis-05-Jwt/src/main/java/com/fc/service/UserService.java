package com.fc.service;

import com.fc.vo.ResultVO;

public interface UserService {
    ResultVO getVerifyCode(String phone);

    ResultVO login(String phone, String code);

    ResultVO logout(String token);
}
