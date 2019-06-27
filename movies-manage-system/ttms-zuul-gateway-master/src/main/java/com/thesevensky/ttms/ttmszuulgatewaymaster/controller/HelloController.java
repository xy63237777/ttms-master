package com.thesevensky.ttms.ttmszuulgatewaymaster.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.PlanAndLockSeats;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatus;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.SeatAndStatusList;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.Movies;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.user.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class HelloController {


    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MoviesClientService moviesClientService = null;

    @GetMapping("/hello")
    public PlanAndLockSeats hello(HttpServletRequest httpRequest) {
        return moviesClientService.test();
    }

    @GetMapping("/kk")
    @PreAuthorize("permitAll()")
    public String kk() {
        return "kk";
    }

    @GetMapping("/user")
    public Authentication user(Authentication user) {
        System.out.println(user);
        System.out.println(user.getDetails());
        return user;
    }

    @GetMapping("/admin/hello")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public HttpMessage getAdminHello() {
        return new HttpMessage();
    }

    @RequestMapping("/ouser")
    public Principal user(Principal user){
        return user;
    }



    @RequestMapping("/me")
    @PreAuthorize("permitAll()")
    public Object me(Authentication user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().getClass());
        System.out.println(authentication.getPrincipal());
        if(user == null) {
            return "没有";
        } else {
            Object details = user.getDetails();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails)user.getDetails();
            System.out.println(details.getClass());
            System.out.println(oAuth2AuthenticationDetails.getTokenType() + " " + oAuth2AuthenticationDetails.getTokenValue());
        }
        return "有";
    }

    @Autowired
    private UserClientService userClientService = null;

    @PostMapping("/tt1")
    @PreAuthorize("permitAll()")
    public String tt1(MultipartFile[] files,  Movies movies) throws JsonProcessingException {

        return "ok";
    }

    @GetMapping("/s1")
    @PreAuthorize("isAuthenticated()")
    public String s1() {
        return "认证的权限";
    }

    @GetMapping("/s2")
    @PreAuthorize("hasAnyAuthority('root','admin','hallAdmin')")
    public String s2() {
        return "管理员";
    }

    @GetMapping("/s3")
    @PreAuthorize("permitAll()")
    public SeatAndStatusList getKK() {
        SeatAndStatusList seatAndStatusList = new SeatAndStatusList();
        List<SeatAndStatus> seatAndStatus = new ArrayList<>();
        SeatAndStatus seatAndStatus1 = new SeatAndStatus(1234L,1);
        SeatAndStatus seatAndStatus2 = new SeatAndStatus(12345L,1);
        seatAndStatus.add(seatAndStatus1);
        seatAndStatus.add(seatAndStatus2);
        seatAndStatusList.setList(seatAndStatus);
        return seatAndStatusList;
    }
}
