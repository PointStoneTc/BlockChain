package com.chain.wp.coin.page;

import java.util.ArrayList;
import java.util.List;

import com.chain.wp.coin.entity.Asset;

public class AssetInfoExchange {
    private Asset asset;
    private List<ExchangeGeneral> list;

    public AssetInfoExchange() {
        list = new ArrayList<ExchangeGeneral>();
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public List<ExchangeGeneral> getList() {
        return list;
    }

    public void setList(List<ExchangeGeneral> list) {
        this.list = list;
    }
}
