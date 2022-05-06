package com.fc.service.impl;

import com.fc.dao.TbNoteMapper;
import com.fc.dao.TbNoteTypeMapper;
import com.fc.entity.TbNote;
import com.fc.entity.TbNoteExample;
import com.fc.entity.TbNoteType;
import com.fc.entity.TbNoteTypeExample;
import com.fc.service.TypeService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TbNoteTypeMapper typeMapper;
    @Autowired
    private TbNoteMapper noteMapper;

    @Override
    public List<TbNoteType> getTypes(Integer id) {
        TbNoteTypeExample example = new TbNoteTypeExample();

        TbNoteTypeExample.Criteria criteria = example.createCriteria();

        criteria.andUserIdEqualTo(id);

        return typeMapper.selectByExample(example);
    }

    @Override
    public ResultVO add(TbNoteType type) {
        ResultVO vo = new ResultVO();

        int affectedRows = typeMapper.insertSelective(type);

        if (affectedRows > 0) {
            vo.setMessage("添加成功");
            vo.setCode(1);
            vo.setSuccess(true);
            vo.setData(type.getId());
        } else {
            vo.setMessage("添加失败");
            vo.setCode(0);
            vo.setSuccess(false);
        }

        return vo;
    }

    @Override
    public ResultVO update(TbNoteType type) {
        ResultVO vo = new ResultVO();

        int affectedRows = typeMapper.updateByPrimaryKeySelective(type);

        if (affectedRows > 0) {
            vo.setMessage("修改成功");
            vo.setCode(1);
            vo.setSuccess(true);
            vo.setData(type.getId());
        } else {
            vo.setMessage("修改失败");
            vo.setCode(0);
            vo.setSuccess(false);
        }

        return vo;
    }

    @Override
    public int delete(Integer id) {
        int result = 0;

        TbNoteExample example = new TbNoteExample();

        TbNoteExample.Criteria criteria = example.createCriteria();

        criteria.andTypeIdEqualTo(id);

        List<TbNote> notes = noteMapper.selectByExample(example);

        // 如果当前类型下还有日记，就不能进行删除操作
        if (notes.size() > 0) {
            result = -1;
        } else {
            // 只有当前类别下没有日记时才能够进行删除操作
            int affectedRows = typeMapper.deleteByPrimaryKey(id);

            if (affectedRows > 0) {
                result = 1;
            }
        }

        return result;
    }
}
