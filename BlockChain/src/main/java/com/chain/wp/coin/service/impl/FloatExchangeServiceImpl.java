package com.chain.wp.coin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.FloatExchangeMapper;
import com.chain.wp.coin.entity.FloatExchange;
import com.chain.wp.coin.service.FloatExchangeServiceI;

@Service("floatExchangeService")
public class FloatExchangeServiceImpl implements FloatExchangeServiceI {
    @Autowired
    private FloatExchangeMapper floatExchangeDao;

    public int deleteByPrimaryKey(String id) {
        return floatExchangeDao.deleteByPrimaryKey(id);
    }

    public int insert(FloatExchange record) {
        return floatExchangeDao.insert(record);
    }

    public int insertSelective(FloatExchange record) {
        return floatExchangeDao.insertSelective(record);
    }

    public FloatExchange selectByPrimaryKey(String id) {
        return floatExchangeDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(FloatExchange record) {
        return floatExchangeDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(FloatExchange record) {
        return floatExchangeDao.updateByPrimaryKey(record);
    }

    public void insertByBatch(List<FloatExchange> floatExchangeList) {
        floatExchangeDao.insertByBatch(floatExchangeList);
    }

}
