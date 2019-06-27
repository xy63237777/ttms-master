package com.thesevensky.ttms.ttmsproviderusermaster.pojo;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/10 15:39
 * @Version 1.0
 */
public class OAuth2TokenBody implements Serializable {
    private static final long serialVersionUID = 4958666129560248759L;
    private String username;
    private String password;
    private String grant_type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Override
    public String toString() {
        return "OAuth2TokenBody{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", grant_type='" + grant_type + '\'' +
                '}';
    }
}
