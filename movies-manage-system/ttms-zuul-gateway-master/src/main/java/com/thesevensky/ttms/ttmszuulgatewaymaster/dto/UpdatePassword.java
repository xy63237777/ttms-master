package com.thesevensky.ttms.ttmszuulgatewaymaster.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 2:21
 * @Version 1.0
 */
public class UpdatePassword {

    @NotEmpty(message = "旧密码不能为空")
    private String password;
    @Pattern(regexp = "^[a-zA-Z]+[0-9a-zA-Z]+$", message = "新密码以字母开头,不可以包含特殊符号")
    @Length(min = 6,max = 16, message = "新密码的长度是6-16位")
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;

    public UpdatePassword() {
    }

    public UpdatePassword(String password, String newPassword) {
        this.password = password;
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePassword{" +
                "password='" + password + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
