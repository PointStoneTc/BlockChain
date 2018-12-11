package com.chain.wp.coin.page;

import java.math.BigDecimal;
import java.util.Date;

public class ExchangeMarkInfoDetail implements java.io.Serializable {
    private static final long serialVersionUID = -769086772392354172L;
    private int echangeId;
    private int numNarketPairs;
    private String echangeName;
    private String echangeSlug;
    private String marketPair;
    private int marketPairBaseId;
    private String marketPairBaseSymbol;
    private String marketPairType;
    private int marketPairQuoteId;
    private String marketPairQuoteSymbol;
    private BigDecimal quotePirce;
    private BigDecimal quoteVolume24hBase;
    private BigDecimal quoteVolume24hQuote;
    private BigDecimal BTCPrice;
    private BigDecimal BTCVolume24h;
    private Date lastUpdated;

    public ExchangeMarkInfoDetail() {}

    public int getEchangeId() {
        return echangeId;
    }

    public void setEchangeId(int echangeId) {
        this.echangeId = echangeId;
    }

    public int getNumNarketPairs() {
        return numNarketPairs;
    }

    public void setNumNarketPairs(int numNarketPairs) {
        this.numNarketPairs = numNarketPairs;
    }

    public String getEchangeName() {
        return echangeName;
    }

    public void setEchangeName(String echangeName) {
        this.echangeName = echangeName;
    }

    public String getEchangeSlug() {
        return echangeSlug;
    }

    public void setEchangeSlug(String echangeSlug) {
        this.echangeSlug = echangeSlug;
    }

    public String getMarketPair() {
        return marketPair;
    }

    public void setMarketPair(String marketPair) {
        this.marketPair = marketPair;
    }

    public int getMarketPairBaseId() {
        return marketPairBaseId;
    }

    public void setMarketPairBaseId(int marketPairBaseId) {
        this.marketPairBaseId = marketPairBaseId;
    }

    public String getMarketPairBaseSymbol() {
        return marketPairBaseSymbol;
    }

    public void setMarketPairBaseSymbol(String marketPairBaseSymbol) {
        this.marketPairBaseSymbol = marketPairBaseSymbol;
    }

    public String getMarketPairType() {
        return marketPairType;
    }

    public void setMarketPairType(String marketPairType) {
        this.marketPairType = marketPairType;
    }

    public int getMarketPairQuoteId() {
        return marketPairQuoteId;
    }

    public void setMarketPairQuoteId(int marketPairQuoteId) {
        this.marketPairQuoteId = marketPairQuoteId;
    }

    public String getMarketPairQuoteSymbol() {
        return marketPairQuoteSymbol;
    }

    public void setMarketPairQuoteSymbol(String marketPairQuoteSymbol) {
        this.marketPairQuoteSymbol = marketPairQuoteSymbol;
    }

    public BigDecimal getQuotePirce() {
        return quotePirce;
    }

    public void setQuotePirce(BigDecimal quotePirce) {
        this.quotePirce = quotePirce;
    }

    public BigDecimal getQuoteVolume24hBase() {
        return quoteVolume24hBase;
    }

    public void setQuoteVolume24hBase(BigDecimal quoteVolume24hBase) {
        this.quoteVolume24hBase = quoteVolume24hBase;
    }

    public BigDecimal getQuoteVolume24hQuote() {
        return quoteVolume24hQuote;
    }

    public void setQuoteVolume24hQuote(BigDecimal quoteVolume24hQuote) {
        this.quoteVolume24hQuote = quoteVolume24hQuote;
    }

    public BigDecimal getBTCPrice() {
        return BTCPrice;
    }

    public void setBTCPrice(BigDecimal bTCPrice) {
        BTCPrice = bTCPrice;
    }

    public BigDecimal getBTCVolume24h() {
        return BTCVolume24h;
    }

    public void setBTCVolume24h(BigDecimal bTCVolume24h) {
        BTCVolume24h = bTCVolume24h;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getPriceDouble() {
        return quotePirce.toPlainString();
    }
}
