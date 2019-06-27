package com.thesevensky.ttms.ttmsfileuploadmaster.config;

import com.thesevensky.ttms.ttmsfileuploadmaster.web.ResponseHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/7 1:08
 * @Version 1.0
 */
@Configuration
@EnableAspectJAutoProxy
public class HttpServerConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean () {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setFilter(new ResponseHeaderFilter());
        return filterRegistrationBean;
    }
}