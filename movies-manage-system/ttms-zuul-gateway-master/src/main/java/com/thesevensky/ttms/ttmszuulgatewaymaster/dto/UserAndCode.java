package com.thesevensky.ttms.ttmszuulgatewaymaster.dto;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/12 0:37
 * @Version 1.0
 */
public class UserAndCode implements Serializable {

    private static final long serialVersionUID = 320579682686165719L;
    @Valid
    @ApiModelProperty(value = "用户",required = true)
    private User user;

    @NotEmpty(message = "验证码不能为空")
    private String code;

    public UserAndCode() {
    }

    public UserAndCode(User user, @NotEmpty(message = "验证码不能为空") String code) {
        this.user = user;
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserAndCode{" +
                "user=" + user +
                ", code='" + code + '\'' +
                '}';
    }
}
