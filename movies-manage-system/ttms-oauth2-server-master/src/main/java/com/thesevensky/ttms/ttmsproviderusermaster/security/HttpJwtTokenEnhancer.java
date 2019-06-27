package com.thesevensky.ttms.ttmsproviderusermaster.security;

import com.thesevensky.ttms.ttmsproviderusermaster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/23 17:53
 * @Version 1.0
 */
@Component
public class HttpJwtTokenEnhancer implements TokenEnhancer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService = null;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        String principal = (String)oAuth2Authentication.getPrincipal();
        logger.info(principal + " --- 用户 登录成功");
        userService.userForLoginSuccess(principal);
        return oAuth2AccessToken;
    }
}
