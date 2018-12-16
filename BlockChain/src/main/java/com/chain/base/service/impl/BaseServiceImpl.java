package com.chain.base.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.base.dao.BaseMapper;
import com.chain.base.service.BaseServiceI;

@Service("baseService")
public class BaseServiceImpl implements BaseServiceI {
  @Autowired
  private BaseMapper baseDao;

  @Override
  public List<LinkedHashMap<String, Object>> superManagerSelect(String sql) {
    // TODO Auto-generated method stub
    return baseDao.superManagerSelect(sql);
  }

}
