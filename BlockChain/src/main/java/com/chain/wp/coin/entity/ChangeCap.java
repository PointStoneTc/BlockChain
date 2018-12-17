package com.chain.wp.coin.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产变化pojo类，应用在交易所浮动API数据抓取功能中...
 * 
 * @author zhaoqi
 *
 */
public class ChangeCap implements java.io.Serializable {
    private static final long serialVersionUID = -6443129463624271682L;
    private BigDecimal volume24h;
    private BigDecimal percentChange1h;
    private BigDecimal percentChange24h;
    private BigDecimal percentChange7d;
    private Date lastUpdated;
    private BigDecimal change24h;

    public ChangeCap() {};

    public ChangeCap(BigDecimal volume24h, BigDecimal percentChange1h, BigDecimal percentChange24h, BigDecimal percentChange7d, Date lastUpdated, BigDecimal change24h) {
        this.volume24h = volume24h;
        this.percentChange1h = percentChange1h;
        this.percentChange24h = percentChange24h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
        this.change24h = change24h;
    }

    public BigDecimal getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(BigDecimal volume24h) {
        this.volume24h = volume24h;
    }

    public BigDecimal getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(BigDecimal percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public BigDecimal getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(BigDecimal percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public BigDecimal getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(BigDecimal percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public BigDecimal getChange24h() {
        return change24h;
    }

    public void setChange24h(BigDecimal change24h) {
        this.change24h = change24h;
    }
}
