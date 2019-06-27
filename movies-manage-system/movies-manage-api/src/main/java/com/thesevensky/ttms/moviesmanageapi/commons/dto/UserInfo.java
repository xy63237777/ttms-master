package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/13 13:26
 * @Version 1.0
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8267394071803669658L;
    private String key;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String key, String password) {
        this.key = key;
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "key='" + key + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
