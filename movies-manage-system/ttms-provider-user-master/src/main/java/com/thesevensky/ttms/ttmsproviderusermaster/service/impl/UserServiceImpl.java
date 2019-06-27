package com.thesevensky.ttms.ttmsproviderusermaster.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.file.ImageFileProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.exception.UserException;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSFileUtils;
import com.thesevensky.ttms.moviesmanageapi.commons.until.TTMSPageHolder;
import com.thesevensky.ttms.moviesmanageapi.constants.client.ClientImageConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.user.AuthorityConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;

import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailTemplate;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.AuthorityDao;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.UserDao;
import com.thesevensky.ttms.ttmsproviderusermaster.service.AuthorityService;
import com.thesevensky.ttms.ttmsproviderusermaster.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 18:56
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao = null;

    @Autowired
    private PasswordEncoder passwordEncoder = null;

    @Autowired
    private EmailTemplate emailTemplate = null;

    @Autowired
    private AuthorityDao authorityDao = null;

    @Autowired
    private AuthorityService authorityService = null;

    @Autowired
    private MasterProperties masterProperties = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        logger.info("注册用户" + user);
        user.setUserImageUrl(masterProperties.getFile().getImage().getDefaultHead());
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        ImageFileProperties image = masterProperties.getFile().getImage();
        logger.info("修改信息" + user);
        user.setUserImageUrl(image.getClientPath() + image.getHeadPath() + TTMSFileUtils.getHashPath(getPrincipal()) + getPrincipal() + ".jpg");
        user.setUserEmail(getPrincipal());
        userDao.updateUser(user);
        logger.info("修改完成" + user);
    }

    @Override
    public void updateUserName(User user) {
        userDao.updateUserName(user);
    }

    @Override
    public void changeUserForPassword(User user) {
        logger.info("修改密码" + user);
        if(!(user.getUserPassword().equals("") || user.getUserPassword() == null)) {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        }
        userDao.changeUserForPassword(user);
    }

    @Override
    public User getUserForMe() {
        String key = getPrincipal();
        logger.info("用户 : " + key + "想得到自己的信息");
        if(StringUtils.isNotEmpty(key) && !key.equals(AuthorityConstants.DEFAULT_USER)) {
            return userDao.findUserByKey(key);
        }
        return null;
    }

    @Override
    public void cmpPasswordForUser(String password) {
        User user = userDao.cmpUserForPassword(getPrincipal());
        if(!passwordEncoder.matches(password, user.getUserPassword())) throw new UserException("密码输入错误");
        logger.info("用户" + getPrincipal() + "验证密码成功");
    }

    @Override
    public void updateForPassword(String password) {
        password = passwordEncoder.encode(password);
        logger.info("用户"+ getPrincipal()+  " 的新密码 : " + password);
        userDao.updateForPassword(password,getPrincipal());
    }

    @Override
    public void cmpUser(String userEmail) {
        User userByKey = userDao.findUserByKey(userEmail);
        if(userByKey != null) throw new UserException("该用户已经存在");
    }

    @Override
    public TTMSPageInfo<User> getAllUserForAuth(byte bit,Integer num) {
        if(bit == 0) throw new UserException("你还想获取root用户信息?");
        Integer authForNumberByMe = userDao.findAuthForNumberByMe(getPrincipal());
        System.out.println("getAllUserForAuth authForNumberByMe " + authForNumberByMe);
        List<Authority> allByNumber = authorityDao.findAllByNumber(authForNumberByMe);
        System.out.println("getAllUserForAuth  getAllUserForAuth " + allByNumber);
        Authority maxAuthority = authorityService.getMaxAuthority(allByNumber);
        System.out.println("getAllUserForAuth maxAuthority " + maxAuthority);
        if(maxAuthority.getAuthorityBit() >= bit) throw new UserException("你可没有权限获取该类型的用户别乱点");
        Page<User> users = PageHelper.startPage(num,
                masterProperties.getMovies().getMoviesPageProperties().getPageSize())
                .doSelectPage(() -> userDao.findAllByAuth(bit) );
        return TTMSPageHolder.changePageForObj(users);
    }

    private String getPrincipal() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void sendMessage(String userEmail) {
        emailTemplate.send(userEmail);
    }

    @Override
    public User showUserInfoForMe() {
        return userDao.showUserInfo(getPrincipal());
    }
}
