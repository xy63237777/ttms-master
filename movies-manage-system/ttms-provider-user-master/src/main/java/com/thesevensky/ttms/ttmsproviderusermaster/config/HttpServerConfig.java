package com.thesevensky.ttms.ttmsproviderusermaster.config;

import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.EmailTemplate;
import com.thesevensky.ttms.ttmsproviderusermaster.commons.mail.impl.DefaultEmailTemplate;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 16:59
 * @Version 1.0
 */
@EnableEurekaClient
@EnableSwagger2
@EnableAspectJAutoProxy
@Configuration
@EnableAsync
public class HttpServerConfig {

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(16);
    }

    @Bean //必须new 一个RestTemplate并放入spring容器当中,否则启动时报错
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);
        return new RestTemplate(httpRequestFactory);
    }

    @Bean
    public EmailTemplate emailTemplate() {
        return new DefaultEmailTemplate();
    }
}
