package com.thesevensky.ttms.ttmszuulgatewaymaster.config;

import com.thesevensky.ttms.moviesmanageapi.constants.url.UrlConstants;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableZuulProxy
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true ,jsr250Enabled = true)
@EnableOAuth2Sso
@EnableResourceServer
public class GatewayConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers("/register.html","/user/register","/me")
//                .permitAll()
//                .and().authorizeRequests().antMatchers(UrlConstants.STATIC_RESOURCES).permitAll()
//        .and().csrf().disable();
        http.authorizeRequests().antMatchers(UrlConstants.STATIC_RESOURCES).permitAll()
                .and().csrf().disable();
    }



//    @Bean
//    public OAuth2WebFilter oAuth2WebFilter() {
//        return new OAuth2WebFilter();
//    }
}
