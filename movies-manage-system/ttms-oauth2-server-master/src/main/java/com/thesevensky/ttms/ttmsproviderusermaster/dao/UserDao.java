package com.thesevensky.ttms.ttmsproviderusermaster.dao;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 18:36
 * @Version 1.0
 */
@Mapper
public interface UserDao {

    public User queryByUserId(String userEmail);

    public User queryByKeyAndPassword(String key);

    public void addUser(User user);

    public void updateUser(User user);

    public void changeUserForPassword(User user);

    public void userForLoginSuccess(String userEmail);

}
