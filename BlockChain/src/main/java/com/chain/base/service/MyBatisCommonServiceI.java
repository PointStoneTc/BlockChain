package com.chain.base.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface MyBatisCommonServiceI {
    /**
     * 
     * @param sql
     * @return
     */
    List<LinkedHashMap<String, Object>> superManagerSelect(String sql);

    long getCount(String sql);
}
