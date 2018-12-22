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

    public int deleteByPrimaryKey(Integer id) {
        return assetDao.deleteByPrimaryKey(id);
    }

    public int insert(Asset record) {
        return assetDao.insert(record);
    }

    public int insertSelective(Asset record) {
        return assetDao.insertSelective(record);
    }

    public Asset selectByPrimaryKey(Integer id) {
        return assetDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Asset record) {
        return assetDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Asset record) {
        return assetDao.updateByPrimaryKey(record);
    }

}
