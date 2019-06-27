package com.thesevensky.ttms.ttmsfileuploadmaster.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/5 16:47
 * @Version 1.0
 */
@Configuration
@MapperScan(value = "com.thesevensky.ttms.ttmsfileuploadmaster.dao")
public class MybatisConfig {
}
