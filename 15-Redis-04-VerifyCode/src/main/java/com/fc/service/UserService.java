package com.fc.service;

import com.fc.vo.ResultVO;

public interface UserService {
    ResultVO getVerifyCode(String username);

    ResultVO login(String username, String password, String code);
}
