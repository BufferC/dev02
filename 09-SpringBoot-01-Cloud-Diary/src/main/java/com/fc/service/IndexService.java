package com.fc.service;

import com.fc.entity.TbNote;
import com.fc.vo.NoteVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IndexService {
    PageInfo<TbNote> page(Integer pageNum, Integer pageSize, Integer userId, Integer typeId, String title, String date);

    List<NoteVO> findCountByDate(Integer id);

    List<NoteVO> findCountByType(Integer id);
}
