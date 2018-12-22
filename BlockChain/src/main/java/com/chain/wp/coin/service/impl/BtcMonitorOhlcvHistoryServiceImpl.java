package com.chain.wp.coin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.BtcMonitorOhlcvHistoryMapper;
import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;
import com.chain.wp.coin.service.BtcMonitorOhlcvHistoryServiceI;

@Service("btcMonitorOhlcvHistoryService")
public class BtcMonitorOhlcvHistoryServiceImpl implements BtcMonitorOhlcvHistoryServiceI {
    @Autowired
    private BtcMonitorOhlcvHistoryMapper btcMonitorOhlcvHistoryDao;

    public int deleteByPrimaryKey(String id) {
        return btcMonitorOhlcvHistoryDao.deleteByPrimaryKey(id);
    }

    public int insert(BtcMonitorOhlcvHistory record) {
        return btcMonitorOhlcvHistoryDao.insert(record);
    }

    public int insertSelective(BtcMonitorOhlcvHistory record) {
        return btcMonitorOhlcvHistoryDao.insertSelective(record);
    }

    public BtcMonitorOhlcvHistory selectByPrimaryKey(String id) {
        return btcMonitorOhlcvHistoryDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(BtcMonitorOhlcvHistory record) {
        return btcMonitorOhlcvHistoryDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(BtcMonitorOhlcvHistory record) {
        return btcMonitorOhlcvHistoryDao.updateByPrimaryKey(record);
    }

    public List<BtcMonitorOhlcvHistory> selectAll(Integer page, Integer size) {
        return btcMonitorOhlcvHistoryDao.selectAll(page, size);
    }

}
