package com.thesevensky.ttms.ttmsproviderusermaster.config;

import com.thesevensky.starter.commons.ClientInfo;
import com.thesevensky.starter.properties.MasterProperties;
import com.thesevensky.ttms.ttmsproviderusermaster.security.HttpJwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private MasterProperties masterProperties = null;

    @Autowired
    private AuthenticationManager authenticationManager = null;

    @Autowired
    private PasswordEncoder passwordEncoder = null;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        List<ClientInfo> clientInfos = masterProperties.getSecurity().getOauth2().getClients();
        assert clientInfos.size() > 0;
        ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> next = null;
        for(int i = 0; i < clientInfos.size(); i++) {
            ClientInfo clientInfo = clientInfos.get(i);
            List<String> redirectUris = clientInfo.getRedirectUris();
            String[] strings = new String[redirectUris.size()];
            for (int j = 0; j < redirectUris.size(); j++) strings[j] = redirectUris.get(j);
            if(next == null) {
                 next = clients.inMemory().withClient(clientInfo.getClientId()).secret(passwordEncoder.encode(clientInfo.getSecret()))
                        .redirectUris(strings).scopes("all").authorizedGrantTypes("password","authorization_code", "refresh_token").and();
            } else if(i == clientInfos.size() -1) {
                next.withClient(clientInfo.getClientId()).secret(passwordEncoder.encode(clientInfo.getSecret()))
                        .redirectUris(strings).scopes("all").authorizedGrantTypes("password","authorization_code","refresh_token");
            } else {
                next = next.withClient(clientInfo.getClientId()).secret(passwordEncoder.encode(clientInfo.getSecret()))
                        .redirectUris(strings).scopes("all").authorizedGrantTypes("password","authorization_code", "refresh_token").and();
            }
        }

    }

    @Autowired
    private TokenEnhancer tokenEnhancer = null;

    @Autowired
    private UserDetailsService userDetailsService = null;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> enhancers = new ArrayList<>();
//        enhancers.add(endpoints.getTokenEnhancer());
//        System.out.println(endpoints.getTokenEnhancer().getClass());
//        enhancers.add(tokenEnhancer);
//        System.out.println(tokenEnhancer.getClass());
//        enhancerChain.setTokenEnhancers(enhancers);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); // 一天有效期
        endpoints.tokenServices(tokenServices);
        endpoints.authenticationManager(authenticationManager);
//        endpoints.tokenStore(jwtTokenStore()).userDetailsService(userDetailsService);
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> enhancers = new ArrayList<>();
//        if(tokenEnhancer != null) enhancers.add(tokenEnhancer);
//        enhancers.add(jwtAccessTokenConverter());
//        if(enhancers.size() >= 1) {
//            enhancerChain.setTokenEnhancers(enhancers);
//            endpoints.tokenEnhancer(enhancerChain);
//        }
//        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(masterProperties.getSecurity().getOauth2().getJwtKey());
        return converter;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addExposedHeader("head1");
        //corsConfiguration.addExposedHeader("Location");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }


}