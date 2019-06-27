package com.thesevensky.ttms.moviesmanageapi.commons.until;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/11 23:32
 * @Version 1.0
 */
public class TTMSTimeUtils {
    private TTMSTimeUtils() {
        throw new AssertionError();
    }

    public static long getLocalDay() {
        return getDay(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    public static long getMin(long time) {
        return getSecond(time) / 60;
    }

    public static long getSecond(long time) {
        return time / 1000;
    }


    public static long getHour(long time) {
        return getMin(time)/60;
    }

    public static long getDay(long time) {
        return getHour(time) / 24;
    }
}
