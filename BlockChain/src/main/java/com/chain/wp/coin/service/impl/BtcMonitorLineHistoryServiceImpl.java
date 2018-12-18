package com.chain.wp.coin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.BtcMonitorLineHistoryMapper;
import com.chain.wp.coin.entity.BtcMonitorLineHistory;
import com.chain.wp.coin.service.BtcMonitorLineHistoryServiceI;

@Service("btcMonitorLineHistoryService")
public class BtcMonitorLineHistoryServiceImpl implements BtcMonitorLineHistoryServiceI {
    @Autowired
    private BtcMonitorLineHistoryMapper btcMonitorLineHistoryDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return btcMonitorLineHistoryDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BtcMonitorLineHistory record) {
        return btcMonitorLineHistoryDao.insert(record);
    }

    @Override
    public int insertSelective(BtcMonitorLineHistory record) {
        return btcMonitorLineHistoryDao.insertSelective(record);
    }

    @Override
    public BtcMonitorLineHistory selectByPrimaryKey(String id) {
        return btcMonitorLineHistoryDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BtcMonitorLineHistory record) {
        return btcMonitorLineHistoryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BtcMonitorLineHistory record) {
        return btcMonitorLineHistoryDao.updateByPrimaryKey(record);
    }

    @Override
    public List<BtcMonitorLineHistory> selectAll(Integer page, Integer size) {
        return btcMonitorLineHistoryDao.selectAll(page, size);
    }

}
