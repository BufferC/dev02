package com.fc.service.impl;

import com.fc.dao.TbNoteMapper;
import com.fc.entity.TbNote;
import com.fc.service.IndexService;
import com.fc.vo.NoteVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private TbNoteMapper noteMapper;

    @Override
    public PageInfo<TbNote> page(Integer pageNum, Integer pageSize, Integer userId, Integer typeId, String title, String date) {
        if (title != null && !title.equals("")) {
            title = "%" + title + "%";
        }

        PageHelper.startPage(pageNum, pageSize);

        List<TbNote> notes = noteMapper.findNoteByUserId(userId, typeId, title, date);

        return new PageInfo<>(notes);
    }

    @Override
    public List<NoteVO> findCountByDate(Integer userId) {
        return noteMapper.findCountByDate(userId);
    }

    @Override
    public List<NoteVO> findCountByType(Integer userId) {
        return noteMapper.findCountByType(userId);
    }
}
