package com.thesevensky.ttms.ttmsserverconfig.master.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Master implements Serializable {

    private static final long serialVersionUID = 6498005805625268603L;
    public final static String DEFAULT_NAME = "Master";
    private String name = DEFAULT_NAME;
    private long time;

    public Master() {
        time = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
