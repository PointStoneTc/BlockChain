package com.chain.wp.coin.page;

import com.chain.wp.coin.entity.SpecificRate;

/**
 * 资产报价
 * 
 * @author zhaoqi
 *
 */
public class AssetQuotation implements java.io.Serializable {
    private static final long serialVersionUID = 4566008353184306169L;
    private double[] series = new double[] {}; // 月度历史
    private SpecificRate specificRate;

    public AssetQuotation() {
        // TODO Auto-generated constructor stub
    }

    public AssetQuotation(double[] series, SpecificRate specificRate) {
        super();
        this.series = series;
        this.specificRate = specificRate;
    }

    public double[] getSeries() {
        return series;
    }

    public void setSeries(double[] series) {
        this.series = series;
    }

    public SpecificRate getSpecificRate() {
        return specificRate;
    }

    public void setSpecificRate(SpecificRate specificRate) {
        this.specificRate = specificRate;
    }

}
