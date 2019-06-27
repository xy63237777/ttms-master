package com.thesevensky.ttms.ttmszuulgatewaymaster.zuul;

import com.alibaba.druid.util.StringUtils;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/19 16:59
 * @Version 1.0
 */
@Configuration
public class FeignHeadConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String value = request.getHeader(name);
                        /**
                         * 遍历请求头里面的属性字段，将logId和token添加到新的请求头中转发到下游服务
                         * */
                        if ("uniqueId".equalsIgnoreCase(name) || "token".equalsIgnoreCase(name)) {
                            logger.debug("添加自定义请求头key:" + name + ",value:" + value);
                            requestTemplate.header(name, value);
                        } else {
                            logger.debug("FeignHeadConfiguration", "非自定义请求头key:" + name + ",value:" + value + "不需要添加!");
                        }
                    }

                } else {
                    logger.warn("FeignHeadConfiguration", "获取请求头失败！");
                }
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (!StringUtils.equals((String) authentication.getPrincipal(), "anonymousUser")
                        && request.getHeader("Authorization") == null) {
                    OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
                    requestTemplate.header("Authorization",
                            oAuth2AuthenticationDetails.getTokenType() + " " + oAuth2AuthenticationDetails.getTokenValue());
                }
            }

        };
    }

}

