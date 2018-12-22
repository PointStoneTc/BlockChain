package com.chain.wp.coin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.coin.dao.SpecificRateMapper;
import com.chain.wp.coin.entity.SpecificRate;
import com.chain.wp.coin.service.SpecificRateServiceI;

@Service("specificRateService")
public class SpecificRateServiceImpl implements SpecificRateServiceI {
    @Autowired
    private SpecificRateMapper specificRateDao;

    public int deleteByPrimaryKey(String id) {
        return specificRateDao.deleteByPrimaryKey(id);
    }

    public int insert(SpecificRate record) {
        return specificRateDao.insert(record);
    }

    public int insertSelective(SpecificRate record) {
        return specificRateDao.insertSelective(record);
    }

    public SpecificRate selectByPrimaryKey(String id) {
        return specificRateDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SpecificRate record) {
        return specificRateDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SpecificRate record) {
        return specificRateDao.updateByPrimaryKey(record);
    }

}
