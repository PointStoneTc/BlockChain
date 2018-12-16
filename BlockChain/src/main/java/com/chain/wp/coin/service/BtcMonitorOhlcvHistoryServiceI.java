package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;

public interface BtcMonitorOhlcvHistoryServiceI {
  int deleteByPrimaryKey(String id);

  int insert(BtcMonitorOhlcvHistory record);

  int insertSelective(BtcMonitorOhlcvHistory record);

  BtcMonitorOhlcvHistory selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(BtcMonitorOhlcvHistory record);

  int updateByPrimaryKey(BtcMonitorOhlcvHistory record);
}
