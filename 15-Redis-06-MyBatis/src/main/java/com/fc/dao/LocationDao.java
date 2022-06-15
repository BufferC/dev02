package com.fc.dao;

import com.fc.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDao {
    List<UserVO> findAll();
}
