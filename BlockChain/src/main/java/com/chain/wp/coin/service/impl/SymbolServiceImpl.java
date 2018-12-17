package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.SymbolMapper;
import com.chain.wp.coin.entity.Symbol;
import com.chain.wp.coin.service.SymbolServiceI;

@Service("symbolService")
public class SymbolServiceImpl implements SymbolServiceI {
    @Autowired
    private SymbolMapper symbolDao;

    @Override
    public int deleteByPrimaryKey(Integer id, String symbolId) {
        return symbolDao.deleteByPrimaryKey(id, symbolId);
    }

    @Override
    public int insert(Symbol record) {
        return symbolDao.insert(record);
    }

    @Override
    public int insertSelective(Symbol record) {
        return symbolDao.insertSelective(record);
    }

    @Override
    public Symbol selectByPrimaryKey(Integer id, String symbolId) {
        return symbolDao.selectByPrimaryKey(id, symbolId);
    }

    @Override
    public int updateByPrimaryKeySelective(Symbol record) {
        return symbolDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Symbol record) {
        return symbolDao.updateByPrimaryKey(record);
    }

}
