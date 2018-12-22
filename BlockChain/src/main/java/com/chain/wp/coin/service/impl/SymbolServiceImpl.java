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

    public int deleteByPrimaryKey(Integer id, String symbolId) {
        return symbolDao.deleteByPrimaryKey(id, symbolId);
    }

    public int insert(Symbol record) {
        return symbolDao.insert(record);
    }

    public int insertSelective(Symbol record) {
        return symbolDao.insertSelective(record);
    }

    public Symbol selectByPrimaryKey(Integer id, String symbolId) {
        return symbolDao.selectByPrimaryKey(id, symbolId);
    }

    public int updateByPrimaryKeySelective(Symbol record) {
        return symbolDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Symbol record) {
        return symbolDao.updateByPrimaryKey(record);
    }

}
