package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.LegalCurrencyMapper;
import com.chain.wp.coin.entity.LegalCurrency;
import com.chain.wp.coin.service.LegalCurrencyServiceI;

@Service("legalCurrencyService")
public class LegalCurrencyServiceImpl implements LegalCurrencyServiceI {
    @Autowired
    private LegalCurrencyMapper legalCurrencyDao;

    public int deleteByPrimaryKey(Integer id) {
        return legalCurrencyDao.deleteByPrimaryKey(id);
    }

    public int insert(LegalCurrency record) {
        return legalCurrencyDao.insert(record);
    }

    public int insertSelective(LegalCurrency record) {
        return legalCurrencyDao.insertSelective(record);
    }

    public LegalCurrency selectByPrimaryKey(Integer id) {
        return legalCurrencyDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(LegalCurrency record) {
        return legalCurrencyDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(LegalCurrency record) {
        return legalCurrencyDao.updateByPrimaryKey(record);
    }

}
