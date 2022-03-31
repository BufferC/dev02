package com.fc.service;

import com.fc.entity.TbMusic;
import com.fc.entity.TbSheet;

import java.util.List;
import java.util.Map;

public interface TbSheetService {
    List<TbSheet> findAll();

    List<TbMusic> findSongsBySheet(String sheetName);

    Map<String, Object> insertSheet(TbSheet sheetName);
}
