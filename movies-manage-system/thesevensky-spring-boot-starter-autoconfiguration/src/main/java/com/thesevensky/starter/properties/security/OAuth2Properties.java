package com.thesevensky.starter.properties.security;



import com.thesevensky.starter.commons.ClientInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/17 15:21
 * @Version 1.0
 */
public class OAuth2Properties {
    private String jwtKey = "TheSevenSky_Key";
    private List<ClientInfo> clients = new ArrayList<>();
    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    public List<ClientInfo> getClients() {
        return clients;
    }

    public void setClients(List<ClientInfo> clients) {
        this.clients = clients;
    }
}
