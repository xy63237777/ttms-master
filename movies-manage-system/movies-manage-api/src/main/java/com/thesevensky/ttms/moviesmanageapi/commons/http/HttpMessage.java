package com.thesevensky.ttms.moviesmanageapi.commons.http;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 21:03
 * @Version 1.0
 */
public class HttpMessage implements Serializable {

    private static final long serialVersionUID = -390907216665272483L;
    private String code = "200";
    private Object msg = "操作完成";

    public HttpMessage() {
    }

    public HttpMessage(String code) {
        this.code = code;
    }

    public HttpMessage(Object msg) {
        this.msg = msg;
    }

    public HttpMessage(String code, Object msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HttpMessage{" +
                "code='" + code + '\'' +
                ", msg=" + msg +
                '}';
    }
}
