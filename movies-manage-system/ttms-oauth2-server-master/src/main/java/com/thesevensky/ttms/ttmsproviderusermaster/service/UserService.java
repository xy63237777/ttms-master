package com.thesevensky.ttms.ttmsproviderusermaster.service;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 18:56
 * @Version 1.0
 */

public interface UserService {

    public User queryByUserId(String userId);

    public User queryByKeyAndPassword(String key, String password);

    public void addUser(User user);

    public void updateUser(User user);

    public void changeUserForPassword(User user);

    public User queryByKeyForClient(String key);

    public void userForLoginSuccess(String userEmail);
}
