package com.thesevensky.ttms.moviesmanageapi.commons.until;

import com.github.pagehelper.Page;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/26 20:17
 * @Version 1.0
 */
public class TTMSPageHolder {
    private TTMSPageHolder() {
        throw new AssertionError();
    }

    public static <K> TTMSPageInfo<K> changePageForObj(Page<K> page) {
        return new TTMSPageInfo<>(page.getResult(),page.getTotal(),page.getPages(),page.getPageNum());
    }
}
