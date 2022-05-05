package com.fc.service.impl;

import com.fc.dao.TbNoteMapper;
import com.fc.service.NoteService;
import com.fc.vo.NoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private TbNoteMapper noteMapper;

    @Override
    public NoteVO findById(Integer id) {
        return noteMapper.findById(id);
    }
}
