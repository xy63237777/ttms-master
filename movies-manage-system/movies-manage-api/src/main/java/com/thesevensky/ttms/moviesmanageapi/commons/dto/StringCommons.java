package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 21:44
 * @Version 1.0
 */
public class StringCommons implements Serializable {

    private static final long serialVersionUID = -82606405925723674L;
    private String msg;

    public StringCommons() {
    }

    public StringCommons(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "StringCommons{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
