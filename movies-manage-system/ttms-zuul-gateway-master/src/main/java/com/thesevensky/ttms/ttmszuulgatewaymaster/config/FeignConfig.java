package com.thesevensky.ttms.ttmszuulgatewaymaster.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


/**
 * @Author TheSevenSky
 * @Date: 2019/6/4 16:42
 * @Version 1.0
 */
@Configuration
public class FeignConfig {

//    @Autowired
//    private ObjectFactory<HttpMessageConverters> messageConverters;


    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
        //return new SpringMultipartEncoder(new SpringEncoder(messageConverters));
        //return new SpringMultipartEncoder();
    }
}
