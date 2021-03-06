package com.chain.wp.coin.dao;

import org.apache.ibatis.annotations.Mapper;

import com.chain.wp.coin.entity.BtcMonitorRateHistory;

@Mapper
public interface BtcMonitorRateHistoryMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * cc_btc_monitor_rate_history
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * cc_btc_monitor_rate_history
     *
     * @mbggenerated
     */
    int insert(BtcMonitorRateHistory record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * cc_btc_monitor_rate_history
     *
     * @mbggenerated
     */
    int insertSelective(BtcMonitorRateHistory record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * cc_btc_monitor_rate_history
     *
     * @mbggenerated
     */
    BtcMonitorRateHistory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * cc_btc_monitor_rate_history
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BtcMonitorRateHistory record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * cc_btc_monitor_rate_history
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BtcMonitorRateHistory record);
}
