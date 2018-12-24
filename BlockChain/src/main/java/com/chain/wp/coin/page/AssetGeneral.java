package com.chain.wp.coin.page;

import java.math.BigDecimal;

/**
 * 资产基本信息
 * 
 * @author zhaoqi
 *
 */
public class AssetGeneral implements java.io.Serializable {
    private static final long serialVersionUID = 5725448793896943806L;
    private String symbol;
    private String name;
    private BigDecimal tradeVolume;
    private StringBuffer avalableExchanges;

    public AssetGeneral() {
        avalableExchanges = new StringBuffer();
    }

    public AssetGeneral(String symbol, String name, BigDecimal tradeVolume, StringBuffer avalableExchanges) {
        this.symbol = symbol;
        this.name = name;
        this.tradeVolume = tradeVolume;
        this.avalableExchanges = avalableExchanges;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(BigDecimal tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public StringBuffer getAvalableExchanges() {
        return avalableExchanges;
    }

    public void setAvalableExchanges(StringBuffer avalableExchanges) {
        this.avalableExchanges = avalableExchanges;
    }
}
