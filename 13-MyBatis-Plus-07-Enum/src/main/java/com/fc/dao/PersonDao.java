package com.fc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends BaseMapper<Person> {
}
