package com.thesevensky.starter.config;

import com.thesevensky.starter.properties.MasterProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MasterProperties.class)
public class PropertiesConfig {
}
