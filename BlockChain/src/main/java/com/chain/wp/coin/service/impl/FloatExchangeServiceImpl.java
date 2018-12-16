package com.chain.wp.coin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.FloatExchangeMapper;
import com.chain.wp.coin.entity.FloatExchange;
import com.chain.wp.coin.service.FloatExchangeServiceI;

@Service("floatExchangeService")
public class FloatExchangeServiceImpl implements FloatExchangeServiceI {
  @Autowired
  private FloatExchangeMapper floatExchangeDao;

  @Override
  public int deleteByPrimaryKey(String id) {
    return floatExchangeDao.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(FloatExchange record) {
    return floatExchangeDao.insert(record);
  }

  @Override
  public int insertSelective(FloatExchange record) {
    return floatExchangeDao.insertSelective(record);
  }

  @Override
  public FloatExchange selectByPrimaryKey(String id) {
    return floatExchangeDao.selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(FloatExchange record) {
    return floatExchangeDao.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(FloatExchange record) {
    return floatExchangeDao.updateByPrimaryKey(record);
  }

  @Override
  public void insertByBatch(List<FloatExchange> floatExchangeList) {
    floatExchangeDao.insertByBatch(floatExchangeList);
  }

}
