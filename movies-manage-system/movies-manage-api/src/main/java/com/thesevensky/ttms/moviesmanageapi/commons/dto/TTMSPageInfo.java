package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/26 20:05
 * @Version 1.0
 */
public class TTMSPageInfo<K> implements Serializable {

    private static final long serialVersionUID = 8612195179376695756L;
    private List<K> results;
    private Long total;
    private Integer nums;
    private Integer thisPage;

    public TTMSPageInfo() {
    }



    public TTMSPageInfo(List<K> results, Long total, Integer nums, Integer thisPage) {
        this.results = results;
        this.total = total;
        this.nums = nums;
        this.thisPage = thisPage;
    }

    public List<K> getResults() {
        return results;
    }

    public void setResults(List<K> results) {
        this.results = results;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Integer getThisPage() {
        return thisPage;
    }

    public void setThisPage(Integer thisPage) {
        this.thisPage = thisPage;
    }

    @Override
    public String toString() {
        return "TTMSPageInfo{" +
                "results=" + results +
                ", total=" + total +
                ", nums=" + nums +
                ", thisPage=" + thisPage +
                '}';
    }
}
