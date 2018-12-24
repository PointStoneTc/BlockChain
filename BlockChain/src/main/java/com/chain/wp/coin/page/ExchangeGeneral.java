package com.chain.wp.coin.page;

import java.util.Date;

public class ExchangeGeneral implements java.io.Serializable {
    private static final long serialVersionUID = 3603957436626344953L;
    private int id;
    private int eid;
    private String name;
    private String slug;
    private int num_market_pairs;
    private Date last_updated;
    private String volume_24h;
    private String volume_24h_adjusted;
    private String volume_7d;
    private String volume_30d;
    private String percent_change_volume_24h;
    private String percent_change_volume_7d;
    private String percent_change_volume_30d;

    public ExchangeGeneral() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
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

    public int getNum_market_pairs() {
        return num_market_pairs;
    }

    public void setNum_market_pairs(int num_market_pairs) {
        this.num_market_pairs = num_market_pairs;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public String getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(String volume_24h) {
        this.volume_24h = volume_24h;
    }

    public String getVolume_24h_adjusted() {
        return volume_24h_adjusted;
    }

    public void setVolume_24h_adjusted(String volume_24h_adjusted) {
        this.volume_24h_adjusted = volume_24h_adjusted;
    }

    public String getVolume_7d() {
        return volume_7d;
    }

    public void setVolume_7d(String volume_7d) {
        this.volume_7d = volume_7d;
    }

    public String getVolume_30d() {
        return volume_30d;
    }

    public void setVolume_30d(String volume_30d) {
        this.volume_30d = volume_30d;
    }

    public String getPercent_change_volume_24h() {
        return percent_change_volume_24h;
    }

    public void setPercent_change_volume_24h(String percent_change_volume_24h) {
        this.percent_change_volume_24h = percent_change_volume_24h;
    }

    public String getPercent_change_volume_7d() {
        return percent_change_volume_7d;
    }

    public void setPercent_change_volume_7d(String percent_change_volume_7d) {
        this.percent_change_volume_7d = percent_change_volume_7d;
    }

    public String getPercent_change_volume_30d() {
        return percent_change_volume_30d;
    }

    public void setPercent_change_volume_30d(String percent_change_volume_30d) {
        this.percent_change_volume_30d = percent_change_volume_30d;
    }
}
