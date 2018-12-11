package com.chain.wp.coin.page;

import java.util.Date;

import com.chain.wp.coin.entity.ChangeCap;

/**
 * 交易所涨跌
 * 
 * @author zhaoqi
 *
 */
public class ExchangeRiseFall implements java.io.Serializable {
    private static final long serialVersionUID = -2208055489346285980L;
    private int id;
    private String name;
    private String slug;
    private int numMarketPairs;
    private String logo;
    private Date lastUpdated;
    private ChangeCap changeCapUnitA;
    private ChangeCap changeCapUnitB;

    public ExchangeRiseFall() {}

    public ExchangeRiseFall(int id, String name, String slug, int numMarketPairs, String logo, Date lastUpdated, ChangeCap changeCapUnitA, ChangeCap changeCapUnitB) {
        super();
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.numMarketPairs = numMarketPairs;
        this.logo = logo;
        this.lastUpdated = lastUpdated;
        this.changeCapUnitA = changeCapUnitA;
        this.changeCapUnitB = changeCapUnitB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getNumMarketPairs() {
        return numMarketPairs;
    }

    public void setNumMarketPairs(int numMarketPairs) {
        this.numMarketPairs = numMarketPairs;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ChangeCap getChangeCapUnitA() {
        return changeCapUnitA;
    }

    public void setChangeCapUnitA(ChangeCap changeCapUnitA) {
        this.changeCapUnitA = changeCapUnitA;
    }

    public ChangeCap getChangeCapUnitB() {
        return changeCapUnitB;
    }

    public void setChangeCapUnitB(ChangeCap changeCapUnitB) {
        this.changeCapUnitB = changeCapUnitB;
    }
}
