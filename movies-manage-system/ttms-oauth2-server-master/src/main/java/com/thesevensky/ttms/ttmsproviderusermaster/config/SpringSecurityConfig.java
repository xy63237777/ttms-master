package com.thesevensky.ttms.ttmsproviderusermaster.config;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/16 20:08
 * @Version 1.0
 */

import com.thesevensky.ttms.moviesmanageapi.constants.url.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
@Order(-1)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder = null;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Autowired
    private AuthenticationSuccessHandler successHandler = null;

    @Autowired
    private AuthenticationFailureHandler failureHandler = null;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http//.formLogin().successHandler(successHandler).failureHandler(failureHandler).and()
//                //.formLogin().loginPage("/oauth2/login").successHandler(successHandler).and()
//                //.requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**","/oa/hello").per
//                //.and()
//                .authorizeRequests()
//                .antMatchers("/oauth/**","/oa/hello").permitAll()
//                .and().authorizeRequests().antMatchers(UrlConstants.STATIC_RESOURCES).permitAll()
//                .and()
//                .formLogin().permitAll().and().cors().and().csrf().disable();
//        http.formLogin().permitAll()
//                .and().authorizeRequests().anyRequest().authenticated()
//                .and().csrf().disable();

        http.requestMatchers().antMatchers(HttpMethod.OPTIONS, "/oauth/**")
                .and()
                .cors()
                .and()
                .csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/check_token");
    }
}
