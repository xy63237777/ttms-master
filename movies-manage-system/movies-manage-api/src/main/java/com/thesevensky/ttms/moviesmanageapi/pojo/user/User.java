package com.thesevensky.ttms.moviesmanageapi.pojo.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 15:47
 * @Version 1.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = -3422191484695131636L;
    @Email(message = "邮箱格式不正确")
    @NotEmpty(message = "邮箱不能为空")
    @Length(min = 3, message = "邮箱长度错误")
    private String userEmail;
    @NotEmpty(message = "昵称不能为空")
    @Length(message = "昵称不能超过20位", min = 1, max = 20)
    private String userName;
    @Pattern(regexp = "^[a-zA-Z]+[0-9a-zA-Z]+$", message = "密码以字母开头,不可以包含特殊符号")
    @Length(min = 6,max = 16, message = "密码的长度是6-16位")
    @NotEmpty(message = "密码不能为空")
    private String userPassword;
    private String userImageUrl;
    private Integer userState = 0;
    private String userCreateTime;
    private String lastLoginTime;
    private Long userLoginCount;
    private String[] userAuthority;

    public User() {
    }

    public User(@Email(message = "邮箱格式不正确") String userEmail, String userName, String userPassword, String userImageUrl, Integer userState, String userCreateTime, String lastLoginTime, Long userLoginCount, String[] userAuthority) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImageUrl = userImageUrl;
        this.userState = userState;
        this.userCreateTime = userCreateTime;
        this.lastLoginTime = lastLoginTime;
        this.userLoginCount = userLoginCount;
        this.userAuthority = userAuthority;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getUserLoginCount() {
        return userLoginCount;
    }

    public void setUserLoginCount(Long userLoginCount) {
        this.userLoginCount = userLoginCount;
    }

    public String[] getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String[] userAuthority) {
        this.userAuthority = userAuthority;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userState=" + userState +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", userLoginCount=" + userLoginCount +
                ", userAuthority=" + Arrays.toString(userAuthority) +
                '}';
    }
}
