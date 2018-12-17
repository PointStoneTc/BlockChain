package com.chain.wp.coin.service;

import com.chain.wp.coin.entity.LegalCurrency;

public interface LegalCurrencyServiceI {
    int deleteByPrimaryKey(Integer id);

    int insert(LegalCurrency record);

    int insertSelective(LegalCurrency record);

    LegalCurrency selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LegalCurrency record);

    int updateByPrimaryKey(LegalCurrency record);
}
