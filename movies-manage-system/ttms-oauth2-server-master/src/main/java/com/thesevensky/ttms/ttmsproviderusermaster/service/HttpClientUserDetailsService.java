package com.thesevensky.ttms.ttmsproviderusermaster.service;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/16 20:06
 * @Version 1.0
 */

import com.thesevensky.ttms.ttmsproviderusermaster.dao.UserDao;
import com.thesevensky.ttms.ttmsproviderusermaster.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Service
public class HttpClientUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService = null;

    @Autowired
    private HttpSession httpSession = null;

    @Autowired
    private UserDao userDao = null;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.thesevensky.ttms.moviesmanageapi.pojo.user.User user = userDao.queryByKeyAndPassword(s);
        if(user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        userService.userForLoginSuccess(s);
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;
        for(String ss : user.getUserAuthority()) {
            if(!flag) {
                stringBuilder.append(ss);
                flag = true;
            } else {
                stringBuilder.append(",").append(ss);
            }
        }
        return new User( user.getUserEmail(), user.getUserPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString()));
    }
}

