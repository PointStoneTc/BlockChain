package com.chain.wp.restapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chain.wp.restapi.dao.CommentUserInfoMapper;
import com.chain.wp.restapi.entity.CommentUserInfo;
import com.chain.wp.restapi.service.CommentUserInfoServiceI;


@Service("commentUserInfoService")
public class CommentUserInfoServiceImpl implements CommentUserInfoServiceI {
    @Autowired
    private CommentUserInfoMapper commentUserInfoDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentUserInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommentUserInfo record) {
        return commentUserInfoDao.insert(record);
    }

    @Override
    public int insertSelective(CommentUserInfo record) {
        return commentUserInfoDao.insertSelective(record);
    }

    @Override
    public CommentUserInfo selectByPrimaryKey(Integer id) {
        return commentUserInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommentUserInfo record) {
        return commentUserInfoDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommentUserInfo record) {
        return commentUserInfoDao.updateByPrimaryKey(record);
    }

    @Override
    public Integer selectOne(CommentUserInfo record) {
        return commentUserInfoDao.selectOne(record);
    }
}
