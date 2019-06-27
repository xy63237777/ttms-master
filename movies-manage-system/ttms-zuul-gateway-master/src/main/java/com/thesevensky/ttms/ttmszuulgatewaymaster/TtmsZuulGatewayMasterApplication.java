package com.thesevensky.ttms.ttmszuulgatewaymaster;

import com.alibaba.druid.pool.DruidDataSource;
import com.thesevensky.starter.config.DruidConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidConfig.class})
@ServletComponentScan
public class TtmsZuulGatewayMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtmsZuulGatewayMasterApplication.class, args);
    }

}
