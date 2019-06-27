package com.thesevensky.ttms.ttmsproviderusermaster.service;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 18:56
 * @Version 1.0
 */

public interface UserService {

    public void addUser(User user);

    public void updateUser(User user);

    public void changeUserForPassword(User user);

    public User getUserForMe();

    public void cmpPasswordForUser(String password);

    public void updateForPassword(String password);

    public void sendMessage(String userEmail);

    public User showUserInfoForMe();

    public void cmpUser(String userEmail);

    public TTMSPageInfo<User> getAllUserForAuth(byte bit,Integer num);

    public void updateUserName(User user);
}
