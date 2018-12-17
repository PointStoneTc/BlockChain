package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.BtcMonitorRateHistoryMapper;
import com.chain.wp.coin.entity.BtcMonitorRateHistory;
import com.chain.wp.coin.service.BtcMonitorRateHistoryServiceI;

@Service("btcMonitorRateHistoryService")
public class BtcMonitorRateHistoryServiceImpl implements BtcMonitorRateHistoryServiceI {
    @Autowired
    private BtcMonitorRateHistoryMapper btcMonitorRateHistoryDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return btcMonitorRateHistoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BtcMonitorRateHistory record) {
        return btcMonitorRateHistoryDao.insert(record);
    }

    @Override
    public int insertSelective(BtcMonitorRateHistory record) {
        return btcMonitorRateHistoryDao.insertSelective(record);
    }

    @Override
    public BtcMonitorRateHistory selectByPrimaryKey(String id) {
        return btcMonitorRateHistoryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BtcMonitorRateHistory record) {
        return btcMonitorRateHistoryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BtcMonitorRateHistory record) {
        return btcMonitorRateHistoryDao.updateByPrimaryKey(record);
    }

}
