package com.chain.base.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.base.dao.MyBatisCommonMapper;
import com.chain.base.service.MyBatisCommonServiceI;

@Service("myBatisCommonService")
public class MyBatisCommonServiceImpl implements MyBatisCommonServiceI {
  @Autowired
  private MyBatisCommonMapper myBatisCommonDao;

  @Override
  public List<LinkedHashMap<String, Object>> superManagerSelect(String sql) {
    return myBatisCommonDao.superManagerSelect(sql);
  }
}
