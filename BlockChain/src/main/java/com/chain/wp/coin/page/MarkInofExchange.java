package com.chain.wp.coin.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供交易所的所有市场
 * 
 * @author zhaoqi
 *
 */
public class MarkInofExchange implements java.io.Serializable {
    private static final long serialVersionUID = -8236363737081826759L;

    private int id;
    private String name;
    private String symbol;
    private int num_market_pairs;
    private String market_pairs;
    private int start;
    List<MarkInofExchangeDetail> exchangeList;

    public MarkInofExchange() {
        exchangeList = new ArrayList<MarkInofExchangeDetail>();
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNum_market_pairs() {
        return num_market_pairs;
    }

    public void setNum_market_pairs(int num_market_pairs) {
        this.num_market_pairs = num_market_pairs;
    }

    public String getMarket_pairs() {
        return market_pairs;
    }

    public void setMarket_pairs(String market_pairs) {
        this.market_pairs = market_pairs;
    }

    public List<MarkInofExchangeDetail> getExchangeList() {
        return exchangeList;
    }

    public void setExchangeList(List<MarkInofExchangeDetail> exchangeList) {
        this.exchangeList = exchangeList;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

}
