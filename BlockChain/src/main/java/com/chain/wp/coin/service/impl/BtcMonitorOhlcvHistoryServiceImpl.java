package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.BtcMonitorOhlcvHistoryMapper;
import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;
import com.chain.wp.coin.service.BtcMonitorOhlcvHistoryServiceI;

@Service("btcMonitorOhlcvHistoryService")
public class BtcMonitorOhlcvHistoryServiceImpl implements BtcMonitorOhlcvHistoryServiceI {
  @Autowired
  private BtcMonitorOhlcvHistoryMapper btcMonitorOhlcvHistoryDao;

  @Override
  public int deleteByPrimaryKey(String id) {
    return btcMonitorOhlcvHistoryDao.deleteByPrimaryKey(id);
  }

  @Override
  public int insert(BtcMonitorOhlcvHistory record) {
    return btcMonitorOhlcvHistoryDao.insert(record);
  }

  @Override
  public int insertSelective(BtcMonitorOhlcvHistory record) {
    return btcMonitorOhlcvHistoryDao.insertSelective(record);
  }

  @Override
  public BtcMonitorOhlcvHistory selectByPrimaryKey(String id) {
    return btcMonitorOhlcvHistoryDao.selectByPrimaryKey(id);
  }

  @Override
  public int updateByPrimaryKeySelective(BtcMonitorOhlcvHistory record) {
    return btcMonitorOhlcvHistoryDao.updateByPrimaryKeySelective(record);
  }

  @Override
  public int updateByPrimaryKey(BtcMonitorOhlcvHistory record) {
    return btcMonitorOhlcvHistoryDao.updateByPrimaryKey(record);
  }

}
