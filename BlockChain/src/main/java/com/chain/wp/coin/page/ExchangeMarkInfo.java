package com.chain.wp.coin.page;

import java.util.ArrayList;
import java.util.List;

import com.chain.wp.coin.entity.Exchange;

/**
 * 交易所下所有的市场配对信息
 * 
 * @author zhaoqi
 *
 */
public class ExchangeMarkInfo implements java.io.Serializable {
    private static final long serialVersionUID = 6872223437437965690L;
    private Exchange exchange;
    private List<ExchangeMarkInfoDetail> markInfoList;

    public ExchangeMarkInfo() {
        markInfoList = new ArrayList<ExchangeMarkInfoDetail>();
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public List<ExchangeMarkInfoDetail> getMarkInfoList() {
        return markInfoList;
    }

    public void setMarkInfoList(List<ExchangeMarkInfoDetail> markInfoList) {
        this.markInfoList = markInfoList;
    }

    public void addMarkInfo(ExchangeMarkInfoDetail detial) {
        markInfoList.add(detial);
    }
}
