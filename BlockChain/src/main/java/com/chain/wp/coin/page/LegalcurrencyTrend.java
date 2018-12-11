package com.chain.wp.coin.page;

import java.util.Date;

import com.chain.wp.coin.entity.ChangeRate;
import com.chain.wp.coin.entity.LegalCurrency;

/**
 * 24小时交法币交易总量
 * 
 * @author zhaoqi
 *
 */
public class LegalcurrencyTrend implements Comparable<LegalcurrencyTrend>, java.io.Serializable {
    private static final long serialVersionUID = 2201587040676978694L;
    private LegalCurrency legalCurrency;
    private ChangeRate changeRate;
    private double proCount24hForBTC; // 24小时交易量兑换成BTC占总比
    private double count24hForBTC; // 24小时交易量兑换成BTC数量
    private Date lastUpdated;

    public LegalcurrencyTrend() {}

    public LegalcurrencyTrend(LegalCurrency legalCurrency, ChangeRate changeRate, double proCount24hForBTC, double count24hForBTC, Date lastUpdated) {
        this.legalCurrency = legalCurrency;
        this.changeRate = changeRate;
        this.proCount24hForBTC = proCount24hForBTC;
        this.count24hForBTC = count24hForBTC;
        this.lastUpdated = lastUpdated;
    }

    public LegalCurrency getLegalCurrency() {
        return legalCurrency;
    }

    public void setLegalCurrency(LegalCurrency legalCurrency) {
        this.legalCurrency = legalCurrency;
    }

    public ChangeRate getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(ChangeRate changeRate) {
        this.changeRate = changeRate;
    }

    public double getProCount24hForBTC() {
        return proCount24hForBTC;
    }

    public void setProCount24hForBTC(double proCount24hForBTC) {
        this.proCount24hForBTC = proCount24hForBTC;
    }

    public double getCount24hForBTC() {
        return count24hForBTC;
    }

    public void setCount24hForBTC(double count24hForBTC) {
        this.count24hForBTC = count24hForBTC;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int compareTo(LegalcurrencyTrend s) {
        if (this.getCount24hForBTC() > s.getCount24hForBTC())
            return 1;
        else if (this.getCount24hForBTC() == s.getCount24hForBTC())
            return 0;

        return -1;
    }
}
