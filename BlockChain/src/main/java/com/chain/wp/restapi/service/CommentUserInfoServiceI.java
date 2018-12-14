package com.chain.wp.restapi.service;

import com.chain.wp.restapi.entity.CommentUserInfo;

public interface CommentUserInfoServiceI {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentUserInfo record);

    int insertSelective(CommentUserInfo record);

    CommentUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentUserInfo record);

    int updateByPrimaryKey(CommentUserInfo record);

    Integer selectOne(CommentUserInfo record);
}
