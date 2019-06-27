package com.thesevensky.ttms.ttmsproviderusermaster.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/20 17:17
 * @Version 1.0
 */
@Configuration
@MapperScan(value = "com.thesevensky.ttms.ttmsproviderusermaster.dao")
public class MybatisConfig {
}
