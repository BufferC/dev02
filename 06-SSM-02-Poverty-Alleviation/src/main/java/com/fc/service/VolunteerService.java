package com.fc.service;

import com.fc.entity.VolunteerRecruitment;
import com.fc.vo.ResultVO;

import java.util.Date;

public interface VolunteerService {
    ResultVO click(Long id, Date lastClickTime);

    ResultVO getList(Integer pageNum, Integer pageSize, Long id);

    ResultVO add(VolunteerRecruitment volunteer);

    ResultVO update(VolunteerRecruitment volunteer);

    ResultVO delete(Long id);
}
