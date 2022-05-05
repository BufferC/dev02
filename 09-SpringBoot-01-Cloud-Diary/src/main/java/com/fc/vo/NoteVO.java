package com.fc.vo;

import com.fc.entity.TbNote;
import lombok.Data;
import lombok.EqualsAndHashCode;

// 用于前端展示日期和类别分类的vo
@EqualsAndHashCode(callSuper = true)
@Data
public class NoteVO extends TbNote {
    private Integer typeId;
    private String groupName;
    private Integer noteCount;
    // 查看某一个日记的详情
    private String typeName;
}
