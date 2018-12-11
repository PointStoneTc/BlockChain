package com.chain.wp.coin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.chain.wp.coin.entity.Exchange;

@Mapper
public interface ExchangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Exchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    int insertSelective(Exchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    Exchange selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Exchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_exchange
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Exchange record);
}