package com.chain.wp.coin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chain.wp.coin.service.CacheReloadServiceI;

@Service("cacheReloadService")
@Transactional
public class CacheReloadServiceImpl implements CacheReloadServiceI {
    private static final Logger logger = LoggerFactory.getLogger(CacheReloadServiceImpl.class);

    @Override
    public boolean exchanges() throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean assets() throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean symbols() throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean xx1() throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

}
