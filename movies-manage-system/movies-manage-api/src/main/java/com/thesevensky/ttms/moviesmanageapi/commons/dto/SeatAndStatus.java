package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 13:57
 * @Version 1.0
 */
public class SeatAndStatus implements Serializable {

    private static final long serialVersionUID = -9009567421105322463L;
    @ApiModelProperty("座位的编号集合")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private long seatId;
    @ApiModelProperty("座位的状态")
    private Integer status;

    public SeatAndStatus() {
    }

    public SeatAndStatus(long seatId, Integer status) {
        this.seatId = seatId;
        this.status = status;
    }

    public long getSeatId() {
        return seatId;
    }

    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SeatAndStatus{" +
                "seatId=" + seatId +
                ", status=" + status +
                '}';
    }
}
