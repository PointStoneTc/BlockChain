package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.BtcMonitorRateHistory;

public interface BtcMonitorRateHistoryServiceI {
    int deleteByPrimaryKey(String id);

    int insert(BtcMonitorRateHistory record);

    int insertSelective(BtcMonitorRateHistory record);

    BtcMonitorRateHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BtcMonitorRateHistory record);

    int updateByPrimaryKey(BtcMonitorRateHistory record);
}
