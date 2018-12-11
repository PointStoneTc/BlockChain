package com.chain.wp.coin.page;

import java.util.LinkedList;
import java.util.Queue;

import com.chain.wp.coin.entity.BtcMonitorLineHistory;
import com.chain.wp.coin.entity.BtcMonitorOhlcvHistory;

public class BtcMonitor implements java.io.Serializable {
    private static final long serialVersionUID = -4167350453182912838L;
    Queue<BtcMonitorLineHistory> line_his_queue;
    Queue<BtcMonitorOhlcvHistory> ohlcv_his_queue;

    public BtcMonitor() {
        line_his_queue = new LinkedList<BtcMonitorLineHistory>();
        ohlcv_his_queue = new LinkedList<BtcMonitorOhlcvHistory>();
    }

    public Queue<BtcMonitorLineHistory> getLine_his_queue() {
        return line_his_queue;
    }

    public void setLine_his_queue(Queue<BtcMonitorLineHistory> line_his_queue) {
        this.line_his_queue = line_his_queue;
    }

    public Queue<BtcMonitorOhlcvHistory> getOhlcv_his_queue() {
        return ohlcv_his_queue;
    }

    public void setOhlcv_his_queue(Queue<BtcMonitorOhlcvHistory> ohlcv_his_queue) {
        this.ohlcv_his_queue = ohlcv_his_queue;
    }
}
