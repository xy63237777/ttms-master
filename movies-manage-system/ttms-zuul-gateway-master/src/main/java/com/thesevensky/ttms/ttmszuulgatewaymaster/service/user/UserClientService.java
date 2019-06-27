package com.thesevensky.ttms.ttmszuulgatewaymaster.service.user;

import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.provider.Providers;
import com.thesevensky.ttms.moviesmanageapi.constants.user.AuthorityConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.user.UserConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import com.thesevensky.ttms.ttmszuulgatewaymaster.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 21:19
 * @Version 1.0
 */
@FeignClient(value = Providers.USERS, configuration = FeignConfig.class)
@RequestMapping(UserConstants.PREFIX)
public interface UserClientService {

    @PostMapping(UserConstants.DEFAULT_USER)
    public User findUser(@RequestBody String key,
                         @RequestHeader("Authorization") String authorization);

//    @PostMapping(UserConstants.USER_FOR_ADD)
//    public HttpMessage addUser(@RequestBody User user);

    @GetMapping(UserConstants.USER_GET_ME)
    public User getUserForMe(@RequestHeader("Authorization") String authorization);

    @PutMapping(value = UserConstants.USER_UPDATE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HttpMessage updateMeInfo(@RequestPart(value = "file", required = false) MultipartFile file,
                                    @RequestParam("userJson") String userJson,
                                    @RequestHeader("Authorization") String authorization);

    @PutMapping(UserConstants.USER_UPDATE_NAME)
    public HttpMessage updateUserForName(@RequestParam("userJson")String userJson,
                                         @RequestHeader("Authorization") String authorization);

    @PutMapping(value = UserConstants.USER_PASSWORD_URL)
    public HttpMessage updateUserForPassword(@RequestParam("password") String password,
                                             @RequestParam("newPassword") String newPassword,
                                             @RequestHeader("Authorization") String authorization);

    @PostMapping(value = UserConstants.USER_FOR_ADD)
    public HttpMessage userAdd(@RequestParam("userJson") String userJson, @RequestParam("code") String code,
                               @RequestHeader("Authorization") String authorization);

    @PostMapping(value = UserConstants.USER_ADD_FOR_SEND_CODE)
    public HttpMessage userSendMessage(@RequestParam("userEmail") String userEmail,
                                       @RequestHeader("Authorization") String authorization);

    @PostMapping(UserConstants.USER_CMP)
    public HttpMessage cmpHasUser(@RequestParam("userEmail") String userEmail,
                                  @RequestHeader("Authorization") String authorization);

    @PostMapping(AuthorityConstants.PREFIX + AuthorityConstants.DEFAULT_ADMIN)
    public HttpMessage updateUserAuth(@RequestParam("userEmailAndAuthority") String userEmailAndAuthority,
                                      @RequestHeader("Authorization") String authorization);

    @GetMapping(AuthorityConstants.PREFIX + AuthorityConstants.DEFAULT_ADMIN)
    public List<Authority> getAuthorities(@RequestHeader("Authorization") String authorization);

    @GetMapping(AuthorityConstants.PREFIX + AuthorityConstants.ADMIN_USER)
    public HttpMessage getAllUserFor(@PathVariable("num") Integer num,
                                     @PathVariable("bit") Byte bit,
                                     @RequestHeader("Authorization") String authorization) ;

}
