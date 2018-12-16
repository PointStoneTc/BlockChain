package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.BtcMonitorLineHistory;

public interface BtcMonitorLineHistoryServiceI {
  int deleteByPrimaryKey(String id);

  int insert(BtcMonitorLineHistory record);

  int insertSelective(BtcMonitorLineHistory record);

  BtcMonitorLineHistory selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(BtcMonitorLineHistory record);

  int updateByPrimaryKey(BtcMonitorLineHistory record);
}
