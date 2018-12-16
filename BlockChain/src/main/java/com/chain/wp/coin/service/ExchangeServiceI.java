package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.Exchange;

public interface ExchangeServiceI {
  int deleteByPrimaryKey(Integer id);

  int insert(Exchange record);

  int insertSelective(Exchange record);

  Exchange selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Exchange record);

  int updateByPrimaryKey(Exchange record);
}
