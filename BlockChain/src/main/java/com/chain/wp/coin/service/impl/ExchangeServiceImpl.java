package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.ExchangeMapper;
import com.chain.wp.coin.entity.Exchange;
import com.chain.wp.coin.service.ExchangeServiceI;

@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeServiceI {
    @Autowired
    private ExchangeMapper exchangeDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return exchangeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Exchange record) {
        return exchangeDao.insert(record);
    }

    @Override
    public int insertSelective(Exchange record) {
        return exchangeDao.insertSelective(record);
    }

    @Override
    public Exchange selectByPrimaryKey(Integer id) {
        return exchangeDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Exchange record) {
        return exchangeDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Exchange record) {
        return exchangeDao.updateByPrimaryKey(record);
    }

}
