package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatList implements Serializable {

    private static final long serialVersionUID = -5351153037640883598L;
    @ApiModelProperty(value = "电影计划编号",required = true)
    private Long planId;
    @ApiModelProperty(value = "当天的电影场次号",required = true)
    private Integer bit;
    @ApiModelProperty("座位的编号集合")
    private List<Long> idList;
    @ApiModelProperty("影厅编号")
    private Long hallId;

    public SeatList() {
    }

    public SeatList(Long planId, Integer bit,String str) {
        String[] strings = str.split(",");
        idList = new ArrayList<>();
        for(String s : strings) {
            idList.add(Long.parseLong(s));
        }
        this.planId = planId;
        this.bit = bit;
    }

    public SeatList(Long planId, Integer bit, List<Long> idList) {
        this.planId = planId;
        this.bit = bit;
        this.idList = idList;
    }

    public SeatList(Long planId, Integer bit, List<Long> idList, Long hallId) {
        this.planId = planId;
        this.bit = bit;
        this.idList = idList;
        this.hallId = hallId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getBit() {
        return bit;
    }

    public void setBit(Integer bit) {
        this.bit = bit;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    @Override
    public String toString() {
        return "SeatList{" +
                "planId=" + planId +
                ", bit=" + bit +
                ", idList=" + idList +
                ", hallId=" + hallId +
                '}';
    }
}
