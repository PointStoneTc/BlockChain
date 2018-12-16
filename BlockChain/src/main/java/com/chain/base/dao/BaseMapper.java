package com.chain.base.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMapper {
    /**
     * 执行任何一条sql
     * @param sql
     * @return
     */
    List<LinkedHashMap<String, Object>> superManagerSelect(String sql);
}
