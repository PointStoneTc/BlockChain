package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.AssetMapper;
import com.chain.wp.coin.entity.Asset;
import com.chain.wp.coin.service.AssetServiceI;

@Service("assetService")
public class AssetServiceImpl implements AssetServiceI {
    @Autowired
    private AssetMapper assetDao;

    @Override
    public int deleteByPrimaryKey(Integer id, String symbol) {
        return assetDao.deleteByPrimaryKey(id, symbol);
    }

    @Override
    public int insert(Asset record) {
        return assetDao.insert(record);
    }

    @Override
    public int insertSelective(Asset record) {
        return assetDao.insertSelective(record);
    }

    @Override
    public Asset selectByPrimaryKey(Integer id, String symbol) {
        return assetDao.selectByPrimaryKey(id, symbol);
    }

    @Override
    public int updateByPrimaryKeySelective(Asset record) {
        return assetDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Asset record) {
        return assetDao.updateByPrimaryKey(record);
    }

}
