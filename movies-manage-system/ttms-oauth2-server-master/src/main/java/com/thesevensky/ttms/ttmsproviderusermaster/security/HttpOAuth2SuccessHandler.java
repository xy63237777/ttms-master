package com.thesevensky.ttms.ttmsproviderusermaster.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.ttmsproviderusermaster.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/24 16:35
 * @Version 1.0
 */
@Component
public class HttpOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserDao userDao = null;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info(authentication.getPrincipal() + " 登录成功");
        HttpMessage httpMessage = new HttpMessage("200", "登录成功");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        userDao.userForLoginSuccess((String) authentication.getPrincipal());
        httpServletResponse.getWriter().println(objectMapper.writeValueAsString(httpMessage));
    }
}
