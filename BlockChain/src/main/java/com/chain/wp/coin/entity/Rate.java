package com.chain.wp.coin.entity;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Rate implements Serializable {
    private static final long serialVersionUID = -9000990289034604170L;
    private double preVal; // 上一个采集点费率
    private double currVal; // 当前费率

    public Rate() {}

    public Rate(double preVal, double currVal) {
        super();
        this.preVal = preVal;
        this.currVal = currVal;
    }

    public double getPreVal() {
        return preVal;
    }

    public void setPreVal(double preVal) {
        this.preVal = preVal;
    }

    public double getCurrVal() {
        return currVal;
    }

    public void setCurrVal(double currVal) {
        this.currVal = currVal;
    }

    /**
     * 计算差额
     * 
     * @return
     */
    public String getDiff() {
        return new DecimalFormat("#.##").format(currVal - preVal);
    }

    /**
     * 计算差率
     * 
     * @return
     */
    public String getDiffRate() {
        if (preVal == 0.0)
            return "0.00%";

        return new DecimalFormat("#.##%").format((currVal - preVal) / preVal);
    }

}
