package com.chain.wp.coin.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * market metrics
 * 
 * @author zhaoqi
 *
 */
public class OneDayMarketCapInfo {
    private double btc_dominance;
    private double eth_dominance;
    private double active_cryptocurrencies;
    private double active_market_pairs;
    private double active_exchanges;
    private Date last_updated;
    private List<Quote> list;

    public OneDayMarketCapInfo() {
        list = new ArrayList<Quote>();
    }

    public double getBtc_dominance() {
        return btc_dominance;
    }

    public void setBtc_dominance(double btc_dominance) {
        this.btc_dominance = btc_dominance;
    }

    public double getEth_dominance() {
        return eth_dominance;
    }

    public void setEth_dominance(double eth_dominance) {
        this.eth_dominance = eth_dominance;
    }

    public double getActive_cryptocurrencies() {
        return active_cryptocurrencies;
    }

    public void setActive_cryptocurrencies(double active_cryptocurrencies) {
        this.active_cryptocurrencies = active_cryptocurrencies;
    }

    public double getActive_market_pairs() {
        return active_market_pairs;
    }

    public void setActive_market_pairs(double active_market_pairs) {
        this.active_market_pairs = active_market_pairs;
    }

    public double getActive_exchanges() {
        return active_exchanges;
    }

    public void setActive_exchanges(double active_exchanges) {
        this.active_exchanges = active_exchanges;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public List<Quote> getList() {
        return list;
    }

    public void setList(List<Quote> list) {
        this.list = list;
    }

    public void addQuote(String name, double total_market_cap, double total_volume_24h) {
        Quote quote = new Quote(name, total_market_cap, total_volume_24h);
        list.add(quote);
    }
}


class Quote {
    private String name;
    private double total_market_cap;
    private double total_volume_24h;

    public Quote() {}


    public Quote(String name, double total_market_cap, double total_volume_24h) {
        this.name = name;
        this.total_market_cap = total_market_cap;
        this.total_volume_24h = total_volume_24h;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal_market_cap() {
        return total_market_cap;
    }

    public void setTotal_market_cap(double total_market_cap) {
        this.total_market_cap = total_market_cap;
    }

    public double getTotal_volume_24h() {
        return total_volume_24h;
    }

    public void setTotal_volume_24h(double total_volume_24h) {
        this.total_volume_24h = total_volume_24h;
    }
}
