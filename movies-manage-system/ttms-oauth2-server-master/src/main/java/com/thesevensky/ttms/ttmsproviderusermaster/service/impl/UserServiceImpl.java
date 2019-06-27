package com.thesevensky.ttms.ttmsproviderusermaster.service.impl;

import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.UserDao;
import com.thesevensky.ttms.ttmsproviderusermaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 18:56
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    @Autowired
    private PasswordEncoder passwordEncoder = null;

    @Override
    public User queryByUserId(String userId) {
        return userDao.queryByUserId(userId);
    }

    /**
     * 暂时没用
     */
    @Override
    public User queryByKeyAndPassword(String key, String password) {
        User user = userDao.queryByKeyAndPassword(key);
        if (passwordEncoder.matches(password, user.getUserPassword())) {
            user.setUserPassword(null);
            return user;
        }
        return null;
    }

    @Override
    public User queryByKeyForClient(String key) {
        return userDao.queryByKeyAndPassword(key);
    }

    @Override
    public void addUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        System.out.println(user);
        System.out.println(user.getUserPassword());
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void changeUserForPassword(User user) {
        if(!(user.getUserPassword().equals("") || user.getUserPassword() == null)) {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
        userDao.changeUserForPassword(user);
    }

    @Override
    @Async
    public void userForLoginSuccess(String userEmail) {
        userDao.userForLoginSuccess(userEmail);
    }
}
