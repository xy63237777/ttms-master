package com.thesevensky.ttms.ttmsserverconfig.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.thesevensky.ttms.ttmsserverconfig.master.dao")
public class MyBatisConfig {
}
