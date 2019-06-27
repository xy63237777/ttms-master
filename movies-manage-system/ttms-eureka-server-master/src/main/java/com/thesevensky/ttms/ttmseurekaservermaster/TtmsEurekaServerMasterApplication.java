package com.thesevensky.ttms.ttmseurekaservermaster;

import com.thesevensky.starter.config.DruidConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DruidConfig.class})
@EnableEurekaServer
public class TtmsEurekaServerMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtmsEurekaServerMasterApplication.class, args);
    }

}
