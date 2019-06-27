package com.thesevensky.ttms.moviesmanageapi.commons.dto;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/13 17:21
 * @Version 1.0
 */
public class UserEmailAndAuthority implements Serializable {

    private static final long serialVersionUID = 327804704253591905L;
    @ApiModelProperty("需要需改用户的用户邮箱")
    private String userEmail;

    private Authority authority;

    public UserEmailAndAuthority() {
    }

    public UserEmailAndAuthority(String userEmail, Authority authority) {
        this.userEmail = userEmail;
        this.authority = authority;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "UserEmailAndAuthority{" +
                "userEmail='" + userEmail + '\'' +
                ", authority=" + authority +
                '}';
    }
}
