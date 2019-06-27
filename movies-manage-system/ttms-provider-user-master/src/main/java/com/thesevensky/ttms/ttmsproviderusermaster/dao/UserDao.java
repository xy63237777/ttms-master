package com.thesevensky.ttms.ttmsproviderusermaster.dao;

import com.thesevensky.ttms.moviesmanageapi.commons.dto.UserEmailAndAuthority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 18:36
 * @Version 1.0
 */
@Mapper
public interface UserDao {

    public void addUser(User user);

    public void updateUser(User user);

    public void changeUserForPassword(User user);

    public User findUserByKey(String key);

    public User cmpUserForPassword(String key);

    public void updateForPassword(@Param("password") String password,@Param("key") String key);

    public User cmpUserForIsExist(String key);

    public User showUserInfo(String key);

    public void updateAuthorityForUser(UserEmailAndAuthority userEmailAndAuthority);

    public List<User> findAllByAuth(byte bit);

    public Integer findAuthForNumberByMe(String userEmail);

    public void updateUserName(User user);
}
