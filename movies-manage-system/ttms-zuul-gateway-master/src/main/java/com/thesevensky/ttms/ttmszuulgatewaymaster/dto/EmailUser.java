package com.thesevensky.ttms.ttmszuulgatewaymaster.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 0:24
 * @Version 1.0
 */
public class EmailUser implements Serializable {
    private static final long serialVersionUID = 851726263929035486L;
    @Email(message = "邮箱格式不正确")
    @NotEmpty(message = "邮箱不能为空")
    @Length(min = 3, message = "邮箱长度错误")
    private String userEmail;

    public EmailUser() {
    }

    public EmailUser(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "EmailUser{" +
                "userEmail='" + userEmail + '\'' +
                '}';
    }
}
