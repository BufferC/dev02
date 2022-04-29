package com.fc.dao;

import com.fc.entity.TbNote;
import com.fc.entity.TbNoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbNoteMapper {
    long countByExample(TbNoteExample example);

    int deleteByExample(TbNoteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbNote record);

    int insertSelective(TbNote record);

    List<TbNote> selectByExampleWithBLOBs(TbNoteExample example);

    List<TbNote> selectByExample(TbNoteExample example);

    TbNote selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbNote record, @Param("example") TbNoteExample example);

    int updateByExampleWithBLOBs(@Param("record") TbNote record, @Param("example") TbNoteExample example);

    int updateByExample(@Param("record") TbNote record, @Param("example") TbNoteExample example);

    int updateByPrimaryKeySelective(TbNote record);

    int updateByPrimaryKeyWithBLOBs(TbNote record);

    int updateByPrimaryKey(TbNote record);
}