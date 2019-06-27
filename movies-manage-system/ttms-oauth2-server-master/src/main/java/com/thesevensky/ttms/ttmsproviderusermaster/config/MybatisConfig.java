package com.thesevensky.ttms.ttmsproviderusermaster.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 18:33
 * @Version 1.0
 */
@Configuration
@MapperScan("com.thesevensky.ttms.ttmsproviderusermaster.dao")
public class MybatisConfig {
}
