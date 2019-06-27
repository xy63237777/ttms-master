package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.auth;

import com.thesevensky.starter.commons.SecurityConstants;
import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.UserInfo;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.user.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 21:31
 * @Version 1.0
 */
@RestController
public class AuthController {

    @Autowired
    private UserClientService userClientService = null;

    @Autowired
    private MasterProperties masterProperties = null;

    @PostMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public User user(String key) {
        System.out.println(key);
        return null;
        //return userClientService.findUser(key);
    }
}
