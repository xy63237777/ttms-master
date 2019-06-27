package com.thesevensky.ttms.moviesmanageapi.enums.movies;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/27 16:50
 * @Version 1.0
 */
public enum MoviesCartStatues {
    /**
     * 默认
     */
    Default((byte)0),
    /**
     * 已经支付订单
     */
    Pay((byte)1),
    /**
     * 已经结束订单
     */
    Ended((byte)2),
    /**
     * 异常订单
     */
    Exception((byte)3);
    private Byte status;

    MoviesCartStatues(Byte status) {
        this.status = status;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
