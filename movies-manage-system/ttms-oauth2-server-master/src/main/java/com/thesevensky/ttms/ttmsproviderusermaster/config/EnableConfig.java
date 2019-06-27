package com.thesevensky.ttms.ttmsproviderusermaster.config;

import com.thesevensky.starter.commons.ClientInfo;
import com.thesevensky.starter.properties.MasterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 18:32
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@EnableEurekaClient
@EnableAsync
public class EnableConfig {

}
