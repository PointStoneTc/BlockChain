package com.chain.wp.coin.page;

import java.math.BigDecimal;
import java.util.Date;

import com.chain.wp.coin.entity.Exchange;

/**
 * 
 * @author zhaoqi
 *
 */
public class MarkInofExchangeDetail implements java.io.Serializable {
	private static final long serialVersionUID = -8496010144260101327L;
	private Exchange exchange;
	private String marketPair;
	private int marketPairBaseID;
	private String marketPairBaseSymbol;
	private String marketPairBaseType;
	private int marketPairQuoteID;
	private String marketPairQuoteType;
	private String marketPairQuoteSymbol;
	private BigDecimal quotePirce;
	private BigDecimal quoteVolume24hBase;
	private BigDecimal quoteVolume24hQuote;
	private Date lastUpdate;
	private BigDecimal convertPrice;
	private BigDecimal convertVolume24h;
	private Date convertlastUpdate;

	public MarkInofExchangeDetail() {

	}

	/**
     * @param exchange
     * @param marketPair
     * @param marketPairBaseID
     * @param marketPairBaseSymbol
     * @param marketPairBaseType
     * @param marketPairQuoteID
     * @param marketPairQuoteType
     * @param marketPairQuoteSymbol
     * @param quotePirce
     * @param quoteVolume24hBase
     * @param quoteVolume24hQuote
     * @param lastUpdate
     * @param convertPrice
     * @param convertVolume24h
     * @param convertlastUpdate
     */
    public MarkInofExchangeDetail(Exchange exchange, String marketPair, int marketPairBaseID, String marketPairBaseSymbol, String marketPairBaseType, int marketPairQuoteID,
            String marketPairQuoteType, String marketPairQuoteSymbol, BigDecimal quotePirce, BigDecimal quoteVolume24hBase, BigDecimal quoteVolume24hQuote, Date lastUpdate,
            BigDecimal convertPrice, BigDecimal convertVolume24h, Date convertlastUpdate) {
        super();
        this.exchange = exchange;
        this.marketPair = marketPair;
        this.marketPairBaseID = marketPairBaseID;
        this.marketPairBaseSymbol = marketPairBaseSymbol;
        this.marketPairBaseType = marketPairBaseType;
        this.marketPairQuoteID = marketPairQuoteID;
        this.marketPairQuoteType = marketPairQuoteType;
        this.marketPairQuoteSymbol = marketPairQuoteSymbol;
        this.quotePirce = quotePirce;
        this.quoteVolume24hBase = quoteVolume24hBase;
        this.quoteVolume24hQuote = quoteVolume24hQuote;
        this.lastUpdate = lastUpdate;
        this.convertPrice = convertPrice;
        this.convertVolume24h = convertVolume24h;
        this.convertlastUpdate = convertlastUpdate;
    }



    public Exchange getExchange() {
		return exchange;
	}

	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	public String getMarketPair() {
		return marketPair;
	}

	public void setMarketPair(String marketPair) {
		this.marketPair = marketPair;
	}

	public int getMarketPairBaseID() {
		return marketPairBaseID;
	}

	public void setMarketPairBaseID(int marketPairBaseID) {
		this.marketPairBaseID = marketPairBaseID;
	}

	public String getMarketPairBaseSymbol() {
		return marketPairBaseSymbol;
	}

	public void setMarketPairBaseSymbol(String marketPairBaseSymbol) {
		this.marketPairBaseSymbol = marketPairBaseSymbol;
	}

	public String getMarketPairBaseType() {
		return marketPairBaseType;
	}

	public void setMarketPairBaseType(String marketPairBaseType) {
		this.marketPairBaseType = marketPairBaseType;
	}

	public int getMarketPairQuoteID() {
		return marketPairQuoteID;
	}

	public void setMarketPairQuoteID(int marketPairQuoteID) {
		this.marketPairQuoteID = marketPairQuoteID;
	}

	public String getMarketPairQuoteType() {
		return marketPairQuoteType;
	}

	public void setMarketPairQuoteType(String marketPairQuoteType) {
		this.marketPairQuoteType = marketPairQuoteType;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

    public BigDecimal getConvertPrice() {
        return convertPrice;
    }

    public void setConvertPrice(BigDecimal convertPrice) {
        this.convertPrice = convertPrice;
    }

    public BigDecimal getConvertVolume24h() {
        return convertVolume24h;
    }

    public void setConvertVolume24h(BigDecimal convertVolume24h) {
        this.convertVolume24h = convertVolume24h;
    }

    public Date getConvertlastUpdate() {
        return convertlastUpdate;
    }

    public void setConvertlastUpdate(Date convertlastUpdate) {
        this.convertlastUpdate = convertlastUpdate;
    }
    public String getPriceDouble() {
        return quotePirce.toPlainString();
    }

}
