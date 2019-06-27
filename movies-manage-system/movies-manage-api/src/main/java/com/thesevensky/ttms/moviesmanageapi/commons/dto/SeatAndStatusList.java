package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonDeserializer;
import com.thesevensky.ttms.moviesmanageapi.commons.jsonserializer.LongJsonSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 13:59
 * @Version 1.0
 */
public class SeatAndStatusList implements Serializable {


    private static final long serialVersionUID = -819989908837563023L;
    private List<SeatAndStatus> list;
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long hallId;
    public SeatAndStatusList() {
    }

    public SeatAndStatusList(List<SeatAndStatus> list, Long hallId) {
        this.list = list;
        this.hallId = hallId;
    }

    public List<SeatAndStatus> getList() {
        return list;
    }

    public void setList(List<SeatAndStatus> list) {
        this.list = list;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    @Override
    public String toString() {
        return "SeatAndStatusList{" +
                "list=" + list +
                ", hallId=" + hallId +
                '}';
    }
}
