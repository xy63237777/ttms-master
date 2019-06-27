package com.thesevensky.starter.properties.security;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 15:51
 * @Version 1.0
 */
public class SecurityProperties {

    private BrowserProperties browserProperties = new BrowserProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();

    public BrowserProperties getBrowserProperties() {
        return browserProperties;
    }

    public void setBrowserProperties(BrowserProperties browserProperties) {
        this.browserProperties = browserProperties;
    }

    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
