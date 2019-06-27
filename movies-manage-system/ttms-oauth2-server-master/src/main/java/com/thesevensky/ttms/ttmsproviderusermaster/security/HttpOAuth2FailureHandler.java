package com.thesevensky.ttms.ttmsproviderusermaster.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/6 15:46
 * @Version 1.0
 */
@Component
public class HttpOAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        HttpMessage httpMessage = new HttpMessage("400", exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(objectMapper.writeValueAsString(httpMessage));
    }
}
