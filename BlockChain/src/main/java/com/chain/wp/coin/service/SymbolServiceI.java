package com.chain.wp.coin.service;

import org.apache.ibatis.annotations.Param;

import com.chain.wp.coin.entity.Symbol;

public interface SymbolServiceI {
  int deleteByPrimaryKey(@Param("id") Integer id, @Param("symbolId") String symbolId);

  int insert(Symbol record);

  int insertSelective(Symbol record);

  Symbol selectByPrimaryKey(@Param("id") Integer id, @Param("symbolId") String symbolId);

  int updateByPrimaryKeySelective(Symbol record);

  int updateByPrimaryKey(Symbol record);
}
