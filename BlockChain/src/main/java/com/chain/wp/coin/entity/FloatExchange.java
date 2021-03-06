package com.chain.wp.coin.entity;

import java.io.Serializable;
import java.util.Date;

public class FloatExchange implements Serializable {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.exchange_id
     *
     * @mbggenerated
     */
    private Integer exchangeId;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.slug
     *
     * @mbggenerated
     */
    private String slug;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.num_market_pairs
     *
     * @mbggenerated
     */
    private Integer numMarketPairs;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.b_volume_24h
     *
     * @mbggenerated
     */
    private Double bVolume24h;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.q_volume_24h
     *
     * @mbggenerated
     */
    private Double qVolume24h;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.last_updated
     *
     * @mbggenerated
     */
    private Date lastUpdated;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column
     * cc_float_exchange.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table
     * cc_float_exchange
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.id
     *
     * @return the value of cc_float_exchange.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.id
     *
     * @param id the value for cc_float_exchange.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.exchange_id
     *
     * @return the value of cc_float_exchange.exchange_id
     *
     * @mbggenerated
     */
    public Integer getExchangeId() {
        return exchangeId;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.exchange_id
     *
     * @param exchangeId the value for cc_float_exchange.exchange_id
     *
     * @mbggenerated
     */
    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.name
     *
     * @return the value of cc_float_exchange.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.name
     *
     * @param name the value for cc_float_exchange.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.slug
     *
     * @return the value of cc_float_exchange.slug
     *
     * @mbggenerated
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.slug
     *
     * @param slug the value for cc_float_exchange.slug
     *
     * @mbggenerated
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.num_market_pairs
     *
     * @return the value of cc_float_exchange.num_market_pairs
     *
     * @mbggenerated
     */
    public Integer getNumMarketPairs() {
        return numMarketPairs;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.num_market_pairs
     *
     * @param numMarketPairs the value for cc_float_exchange.num_market_pairs
     *
     * @mbggenerated
     */
    public void setNumMarketPairs(Integer numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.b_volume_24h
     *
     * @return the value of cc_float_exchange.b_volume_24h
     *
     * @mbggenerated
     */
    public Double getbVolume24h() {
        return bVolume24h;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.b_volume_24h
     *
     * @param bVolume24h the value for cc_float_exchange.b_volume_24h
     *
     * @mbggenerated
     */
    public void setbVolume24h(Double bVolume24h) {
        this.bVolume24h = bVolume24h;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.q_volume_24h
     *
     * @return the value of cc_float_exchange.q_volume_24h
     *
     * @mbggenerated
     */
    public Double getqVolume24h() {
        return qVolume24h;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.q_volume_24h
     *
     * @param qVolume24h the value for cc_float_exchange.q_volume_24h
     *
     * @mbggenerated
     */
    public void setqVolume24h(Double qVolume24h) {
        this.qVolume24h = qVolume24h;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.last_updated
     *
     * @return the value of cc_float_exchange.last_updated
     *
     * @mbggenerated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.last_updated
     *
     * @param lastUpdated the value for cc_float_exchange.last_updated
     *
     * @mbggenerated
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.create_date
     *
     * @return the value of cc_float_exchange.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.create_date
     *
     * @param createDate the value for cc_float_exchange.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database
     * column cc_float_exchange.update_date
     *
     * @return the value of cc_float_exchange.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column
     * cc_float_exchange.update_date
     *
     * @param updateDate the value for cc_float_exchange.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /************ 附加参数 **************/
    private String percent_change_volume_24h;

    public String getPercent_change_volume_24h() {
        return percent_change_volume_24h;
    }

    public void setPercent_change_volume_24h(String percent_change_volume_24h) {
        this.percent_change_volume_24h = percent_change_volume_24h;
    }

}
