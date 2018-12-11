package com.chain.wp.coin.entity;

import java.io.Serializable;
import java.util.Date;

public class SpecificRate implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.base_id
     *
     * @mbggenerated
     */
    private Integer baseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.base_symbol
     *
     * @mbggenerated
     */
    private String baseSymbol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.base_name
     *
     * @mbggenerated
     */
    private String baseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.quote_symbol
     *
     * @mbggenerated
     */
    private String quoteSymbol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.price
     *
     * @mbggenerated
     */
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.volume_24h
     *
     * @mbggenerated
     */
    private Double volume24h;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.percent_change_1h
     *
     * @mbggenerated
     */
    private Double percentChange1h;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.percent_change_24h
     *
     * @mbggenerated
     */
    private Double percentChange24h;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.percent_change_7d
     *
     * @mbggenerated
     */
    private Double percentChange7d;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.market_cap
     *
     * @mbggenerated
     */
    private Double marketCap;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.last_updated
     *
     * @mbggenerated
     */
    private Date lastUpdated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cc_specific_rate.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table cc_specific_rate
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.id
     *
     * @return the value of cc_specific_rate.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.id
     *
     * @param id the value for cc_specific_rate.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.base_id
     *
     * @return the value of cc_specific_rate.base_id
     *
     * @mbggenerated
     */
    public Integer getBaseId() {
        return baseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.base_id
     *
     * @param baseId the value for cc_specific_rate.base_id
     *
     * @mbggenerated
     */
    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.base_symbol
     *
     * @return the value of cc_specific_rate.base_symbol
     *
     * @mbggenerated
     */
    public String getBaseSymbol() {
        return baseSymbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.base_symbol
     *
     * @param baseSymbol the value for cc_specific_rate.base_symbol
     *
     * @mbggenerated
     */
    public void setBaseSymbol(String baseSymbol) {
        this.baseSymbol = baseSymbol == null ? null : baseSymbol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.base_name
     *
     * @return the value of cc_specific_rate.base_name
     *
     * @mbggenerated
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.base_name
     *
     * @param baseName the value for cc_specific_rate.base_name
     *
     * @mbggenerated
     */
    public void setBaseName(String baseName) {
        this.baseName = baseName == null ? null : baseName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.quote_symbol
     *
     * @return the value of cc_specific_rate.quote_symbol
     *
     * @mbggenerated
     */
    public String getQuoteSymbol() {
        return quoteSymbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.quote_symbol
     *
     * @param quoteSymbol the value for cc_specific_rate.quote_symbol
     *
     * @mbggenerated
     */
    public void setQuoteSymbol(String quoteSymbol) {
        this.quoteSymbol = quoteSymbol == null ? null : quoteSymbol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.price
     *
     * @return the value of cc_specific_rate.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.price
     *
     * @param price the value for cc_specific_rate.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.volume_24h
     *
     * @return the value of cc_specific_rate.volume_24h
     *
     * @mbggenerated
     */
    public Double getVolume24h() {
        return volume24h;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.volume_24h
     *
     * @param volume24h the value for cc_specific_rate.volume_24h
     *
     * @mbggenerated
     */
    public void setVolume24h(Double volume24h) {
        this.volume24h = volume24h;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.percent_change_1h
     *
     * @return the value of cc_specific_rate.percent_change_1h
     *
     * @mbggenerated
     */
    public Double getPercentChange1h() {
        return percentChange1h;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.percent_change_1h
     *
     * @param percentChange1h the value for cc_specific_rate.percent_change_1h
     *
     * @mbggenerated
     */
    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.percent_change_24h
     *
     * @return the value of cc_specific_rate.percent_change_24h
     *
     * @mbggenerated
     */
    public Double getPercentChange24h() {
        return percentChange24h;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.percent_change_24h
     *
     * @param percentChange24h the value for cc_specific_rate.percent_change_24h
     *
     * @mbggenerated
     */
    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.percent_change_7d
     *
     * @return the value of cc_specific_rate.percent_change_7d
     *
     * @mbggenerated
     */
    public Double getPercentChange7d() {
        return percentChange7d;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.percent_change_7d
     *
     * @param percentChange7d the value for cc_specific_rate.percent_change_7d
     *
     * @mbggenerated
     */
    public void setPercentChange7d(Double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.market_cap
     *
     * @return the value of cc_specific_rate.market_cap
     *
     * @mbggenerated
     */
    public Double getMarketCap() {
        return marketCap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.market_cap
     *
     * @param marketCap the value for cc_specific_rate.market_cap
     *
     * @mbggenerated
     */
    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.last_updated
     *
     * @return the value of cc_specific_rate.last_updated
     *
     * @mbggenerated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.last_updated
     *
     * @param lastUpdated the value for cc_specific_rate.last_updated
     *
     * @mbggenerated
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.create_date
     *
     * @return the value of cc_specific_rate.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.create_date
     *
     * @param createDate the value for cc_specific_rate.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cc_specific_rate.update_date
     *
     * @return the value of cc_specific_rate.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cc_specific_rate.update_date
     *
     * @param updateDate the value for cc_specific_rate.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}