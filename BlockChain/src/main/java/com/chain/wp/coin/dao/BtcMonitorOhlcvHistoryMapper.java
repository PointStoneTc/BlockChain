package com.chain.wp.coin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;

@Mapper
public interface BtcMonitorOhlcvHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_btc_monitor_ohlcv_history
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_btc_monitor_ohlcv_history
     *
     * @mbggenerated
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(BtcMonitorOhlcvHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_btc_monitor_ohlcv_history
     *
     * @mbggenerated
     */
    int insertSelective(BtcMonitorOhlcvHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_btc_monitor_ohlcv_history
     *
     * @mbggenerated
     */
    BtcMonitorOhlcvHistory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_btc_monitor_ohlcv_history
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BtcMonitorOhlcvHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cc_btc_monitor_ohlcv_history
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BtcMonitorOhlcvHistory record);
}