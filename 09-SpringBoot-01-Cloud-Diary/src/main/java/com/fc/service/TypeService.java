package com.fc.service;

import com.fc.entity.TbNoteType;
import com.fc.vo.ResultVO;

import java.util.List;

public interface TypeService {
    List<TbNoteType> getTypes(Integer id);

    ResultVO add(TbNoteType type);

    ResultVO update(TbNoteType type);

    int delete(Integer id);
}
