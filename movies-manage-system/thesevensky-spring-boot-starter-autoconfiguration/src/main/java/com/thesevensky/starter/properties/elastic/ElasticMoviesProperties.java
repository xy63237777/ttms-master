package com.thesevensky.starter.properties.elastic;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 0:42
 * @Version 1.0
 */
public class ElasticMoviesProperties {

    private String index = "ttms";
    private String type = "movies";

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
