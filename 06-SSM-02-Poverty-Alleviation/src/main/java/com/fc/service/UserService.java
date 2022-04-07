package com.fc.service;

import com.fc.vo.ResultVO;

public interface UserService {
    ResultVO getList(Integer pageNum, Integer pageSize, Long id);
}
