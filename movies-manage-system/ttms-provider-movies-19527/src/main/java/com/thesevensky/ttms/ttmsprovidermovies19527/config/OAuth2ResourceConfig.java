package com.thesevensky.ttms.ttmsprovidermovies19527.config;

import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.starter.properties.movies.MoviesProperties;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesHallConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesSeatConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesTypeConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.url.UrlConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import static com.thesevensky.ttms.moviesmanageapi.constants.url.UrlConstants.STATIC_RESOURCES;

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
        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.GET,MoviesConstants.PREFIX+COMMONS,
                MoviesTypeConstants.PREFIX+COMMONS,
                MoviesSeatConstants.PREFIX+COMMONS).permitAll()
                .and()
                .authorizeRequests().antMatchers(UrlConstants.STATIC_RESOURCES).permitAll()
                .and();
//        http.authorizeRequests().antMatchers("/**") .csrf().disable();
//                .authorizeRequests().anyRequest().authenticated();
        //http.csrf().disable();
    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        System.out.println(oauth2ResourceId);
//        resources.resourceId(oauth2ResourceId).tokenStore(tokenStore());
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(masterProperties.getSecurity().getOauth2().getJwtKey());
//        return converter;
//    }


}
