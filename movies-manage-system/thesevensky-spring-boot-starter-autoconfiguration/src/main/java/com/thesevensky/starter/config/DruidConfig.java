package com.thesevensky.starter.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TheSevenSky
 */
@Configuration
@ConditionalOnClass(DataSource.class)
public class DruidConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties = null;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource initDruidConfig() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setInitialSize(30);
        druidDataSource.setMinIdle(30);
        druidDataSource.setMaxActive(200);
        druidDataSource.setMaxWait(6000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        return druidDataSource;
    }

    @Bean
    @ConditionalOnMissingBean(ServletRegistrationBean.class)
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<Servlet> bean = new ServletRegistrationBean<>(
                new StatViewServlet() ,"/druid/*");
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("loginUsername", "xy63237777");
        map.put("loginPassword", "xy3055789");
        map.put("allow", "");
        bean.setInitParameters(map);
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean(FilterRegistrationBean.class)
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("exclusions","*.css,*.js,/druid/*,*.jpg,*.png");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
