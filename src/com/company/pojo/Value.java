package com.company.pojo;

import com.company.frame.mainServer.transmitter.Swap;

import java.util.concurrent.locks.Condition;


/**
 * Created by zy on 17-8-21.
 */
public class Value {
    private long time;
    private Condition condition;
    private Swap swap;

    public Swap getSwap() {
        return swap;
    }

    public void setSwap(Swap swap) {
        this.swap = swap;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
