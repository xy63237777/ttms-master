package com.thesevensky.starter.properties.security;

import com.thesevensky.starter.commons.SecurityConstants;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 15:51
 * @Version 1.0
 */
public class BrowserProperties {

    private String LOGIN_URL = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    private String LOGIN_MAPPING = SecurityConstants.DEFAULT_UNAUTHENTICATION_URL;
    public String getLOGIN_URL() {
        return LOGIN_URL;
    }

    public void setLOGIN_URL(String LOGIN_URL) {
        this.LOGIN_URL = LOGIN_URL;
    }

    public String getLOGIN_MAPPING() {
        return LOGIN_MAPPING;
    }

    public void setLOGIN_MAPPING(String LOGIN_MAPPING) {
        this.LOGIN_MAPPING = LOGIN_MAPPING;
    }
}
