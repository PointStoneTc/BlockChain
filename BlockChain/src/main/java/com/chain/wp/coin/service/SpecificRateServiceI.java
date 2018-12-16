package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.SpecificRate;

public interface SpecificRateServiceI {
  int deleteByPrimaryKey(String id);

  int insert(SpecificRate record);

  int insertSelective(SpecificRate record);

  SpecificRate selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(SpecificRate record);

  int updateByPrimaryKey(SpecificRate record);
}
