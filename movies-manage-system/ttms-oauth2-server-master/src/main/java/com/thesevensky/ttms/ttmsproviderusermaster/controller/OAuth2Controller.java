package com.thesevensky.ttms.ttmsproviderusermaster.controller;

import com.thesevensky.ttms.ttmsproviderusermaster.dao.AuthorityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/24 16:18
 * @Version 1.0
 */
@RestController
public class OAuth2Controller {

    @Autowired
    private RestTemplate restTemplate = null;

    @Autowired
    AuthorityDao authorityDao = null;

    @RequestMapping("/hello")
    @PreAuthorize("permitAll()")
    public String hello(String ksm) {
        System.out.println(ksm);
        return "Hello Tem";
    }

    @PostMapping("/tt")
    @PreAuthorize("permitAll()")
    public String tt(@RequestParam
                             Map<String, String> parameters, HttpServletRequest servletRequest) {
        System.out.println(servletRequest.getHeader("Authorization"));
        System.out.println(parameters);
        return "Hello Sb";
    }

    @PostMapping("/ttt")
    @PreAuthorize("permitAll()")
    public ResponseEntity<OAuth2AccessToken> postAccessToken(HttpServletRequest request) throws HttpRequestMethodNotSupportedException {
        return restTemplate.postForEntity("http://localhost:11111/oauth/token", request, OAuth2AccessToken.class);
    }

    @GetMapping("/ss")
    @PreAuthorize("permitAll()")
    public String[] getAll() {
        return authorityDao.findAllSimpleName(31);
    }

}
