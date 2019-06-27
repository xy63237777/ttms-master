package com.thesevensky.starter.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/17 15:28
 * @Version 1.0
 */
public class ClientInfo implements Serializable {
    private String clientId;
    private String secret;
    private List<String> redirectUris = new ArrayList<>();

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "clientId='" + clientId + '\'' +
                ", secret='" + secret + '\'' +
                ", redirectUris=" + redirectUris +
                '}';
    }
}
