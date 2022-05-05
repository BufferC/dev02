package com.fc.dao;

import com.fc.entity.TbNote;
import com.fc.entity.TbNoteExample;
import com.fc.vo.NoteVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

    // 根据用户id获取所有的日记
    List<TbNote> findNoteByUserId(@Param("userId") Integer userId, @Param("typeId") Integer typeId, @Param("title") String title, @Param("date") String date);

    // 根据用户id获取分类的日期
    List<NoteVO> findCountByDate(Integer userId);

    // 根据用户id获取分类的类别
    List<NoteVO> findCountByType(Integer userId);

    // 根据id获取对应的日记对象
    NoteVO findById(Integer id);
}