package com.fc.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum Mood {
    Happy("开心"), Sad("伤心"), Angry("生气"), Excited("兴奋"), EMO("emo");

    @EnumValue
    private final String mood;

    Mood(String mood) {
        this.mood = mood;
    }
}
