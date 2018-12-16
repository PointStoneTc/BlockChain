package com.chain.wp.coin.service;

import java.util.List;

import com.chain.wp.coin.entity.FloatExchange;

public interface FloatExchangeServiceI {
  int deleteByPrimaryKey(String id);

  int insert(FloatExchange record);

  int insertSelective(FloatExchange record);

  FloatExchange selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(FloatExchange record);

  int updateByPrimaryKey(FloatExchange record);

  void insertByBatch(List<FloatExchange> floatExchangeList);
}
