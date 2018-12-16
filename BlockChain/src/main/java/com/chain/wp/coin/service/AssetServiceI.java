package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.Asset;

public interface AssetServiceI {
  int deleteByPrimaryKey(Integer id, String symbol);

  int insert(Asset record);

  int insertSelective(Asset record);
  
  Asset selectByPrimaryKey(Integer id, String symbol);

  int updateByPrimaryKeySelective(Asset record);

  int updateByPrimaryKey(Asset record);
}
