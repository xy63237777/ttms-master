package com.thesevensky.ttms.ttmsproviderusermaster.config;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.movies.MoviesProperties;
import com.thesevensky.ttms.moviesmanageapi.constants.url.UrlConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.user.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/18 15:38
 * @Version 1.0
 */
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

//    @Value(value = "${security.oauth2.resource.id}")
//    private String oauth2ResourceId = null;

    private static final String COMMONS = "/**";

    @Autowired
    private MasterProperties masterProperties = null;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        MoviesProperties movies = masterProperties.getMovies();
        http.csrf().disable().authorizeRequests().antMatchers(
                UserConstants.PREFIX+UserConstants.USER_FOR_ADD).permitAll()
                .and().authorizeRequests().antMatchers(UrlConstants.STATIC_RESOURCES).permitAll();
        //.and().authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
