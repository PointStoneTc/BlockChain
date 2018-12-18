package com.chain.wp.coin.service;

import java.util.List;

import com.chain.wp.coin.entity.BtcMonitorLineHistory;

public interface BtcMonitorLineHistoryServiceI {
    int deleteByPrimaryKey(String id);

    int insert(BtcMonitorLineHistory record);

    int insertSelective(BtcMonitorLineHistory record);

    BtcMonitorLineHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BtcMonitorLineHistory record);

    int updateByPrimaryKey(BtcMonitorLineHistory record);

    List<BtcMonitorLineHistory> selectAll(Integer page, Integer size);
}
