package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.StringCommons;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.UserEmailAndAuthority;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.user.AuthorityConstants;
import com.thesevensky.ttms.moviesmanageapi.constants.user.UserConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.Authority;
import com.thesevensky.ttms.moviesmanageapi.pojo.user.User;
import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.dto.EmailUser;
import com.thesevensky.ttms.ttmszuulgatewaymaster.dto.UpdatePassword;
import com.thesevensky.ttms.ttmszuulgatewaymaster.dto.UserAndCode;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.user.UserClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @Author TheSevenSky
 * @Date: 2019/5/12 21:18
 * @Version 1.0
 */
@RestController
@RequestMapping(UserConstants.PREFIX)
public class UserController {

    @Autowired
    private UserClientService userClientService = null;

    private ObjectMapper objectMapper = new ObjectMapper();

//    @PostMapping(ClientConstants.CLENT_PREFIX + UserConstants.USER_FOR_ADD)
//    @PreAuthorize("permitAll()")
//    public HttpMessage addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
//        System.out.println(user);
//        HttpMessage httpMessage = new HttpMessage();
//        if(bindingResult.hasErrors()) {
//            httpMessage.setCode("415");
//            httpMessage.setMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
//            return httpMessage;
//        }
//
//        return userClientService.addUser(user);
//    }

    @GetMapping(ClientConstants.CLENT_PREFIX + UserConstants.USER_GET_ME)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("得到我的个人中心")
    public User getMe(HttpServletRequest request) {
        return userClientService.getUserForMe(request.getHeader("Authorization"));
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public User findUser(UserInfo userInfo) {
//        System.out.println(userInfo);
//        return userClientService.findUser(userInfo);
//    }
    @PutMapping(ClientConstants.CLENT_PREFIX + UserConstants.USER_UPDATE_IMAGE)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("更改用户个人信息")
    public HttpMessage updateUser(MultipartFile file,User user,
                                  HttpServletRequest request) throws JsonProcessingException {
        if(file == null) {
            return userClientService.updateUserForName(objectMapper.writeValueAsString(user),
                    request.getHeader("Authorization"));
        }
        return userClientService.updateMeInfo(file,objectMapper.writeValueAsString(user),
                request.getHeader("Authorization"));
    }

    @PutMapping(ClientConstants.CLENT_PREFIX + UserConstants.USER_PASSWORD_URL)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("修改密码")
    public HttpMessage updateForUserPassword(@RequestBody @Valid UpdatePassword updatePassword,
                                             BindingResult bindingResult,
                                             HttpServletRequest request){
        if(bindingResult.hasErrors()) return validForParam(bindingResult);
        return userClientService.updateUserForPassword(updatePassword.getPassword(),
                updatePassword.getNewPassword(),
                request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX +  UserConstants.USER_FOR_ADD)
    @PreAuthorize("permitAll()")
    @ApiOperation("添加用户 需要带用户和验证码")
    public HttpMessage userAddForClient(@RequestBody @Valid UserAndCode user, BindingResult bindingResult,
                                        HttpServletRequest request) throws JsonProcessingException {
        System.out.println(user);
        if(user.getUser() == null) return new HttpMessage("415", "数据错误");
        if(bindingResult.hasErrors()) return validForParam(bindingResult);
        return userClientService.userAdd(objectMapper.writeValueAsString(user.getUser()), user.getCode(),
                request.getHeader("Authorization"));

    }

    @PostMapping(value = ClientConstants.CLENT_PREFIX + UserConstants.USER_ADD_FOR_SEND_CODE)
    @PreAuthorize("permitAll()")
    @ApiOperation("给邮箱发送验证码")
    public HttpMessage userSendMessageForClient(@RequestBody @Valid EmailUser userEmail,BindingResult bindingResult,
                                       HttpServletRequest request) throws IOException {
        if(bindingResult.hasErrors()) return validForParam(bindingResult);
        HttpMessage httpMessage = userClientService.cmpHasUser(userEmail.getUserEmail(), request.getHeader("Authorization"));
        if(httpMessage.getCode().equals("415")) return httpMessage;
        return userClientService.userSendMessage(userEmail.getUserEmail(), request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX + UserConstants.USER_CMP)
    @PreAuthorize("permitAll()")
    @ApiOperation("验证是否有这个用户")
    public HttpMessage cmpHaveUser(@RequestBody @Valid EmailUser userEmail,
                                   HttpServletRequest request,BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return validForParam(bindingResult);
        return userClientService.cmpHasUser(userEmail.getUserEmail(),request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX + AuthorityConstants.PREFIX + AuthorityConstants.DEFAULT_ADMIN)
    @PreAuthorize("hasAnyAuthority('root','admin')")
    @ApiOperation("修改用户的权限")
    public HttpMessage updateUserAuthClient(@RequestBody UserEmailAndAuthority userEmailAndAuthority,
                                            HttpServletRequest request) throws JsonProcessingException {
        return userClientService.updateUserAuth(objectMapper.writeValueAsString(userEmailAndAuthority),
                request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + AuthorityConstants.PREFIX + AuthorityConstants.DEFAULT_ADMIN)
    @PreAuthorize("hasAnyAuthority('root','admin')")
    @ApiOperation("获取所有权限信息")
    public List<Authority> getAuthoritiesForClient(HttpServletRequest request) {
        return userClientService.getAuthorities(request.getHeader("Authorization"));
    }

    @GetMapping(ClientConstants.CLENT_PREFIX + AuthorityConstants.PREFIX + AuthorityConstants.ADMIN_USER)
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public HttpMessage getAllUserFor(@PathVariable("num") Integer num,
                                     @PathVariable("bit") Byte bit,
                                     HttpServletRequest request) {
        return userClientService.getAllUserFor(num,bit,request.getHeader("Authorization"));
    }

    @ExceptionHandler(value = JsonProcessingException.class)
    public HttpMessage httpMessage(Exception e) {
        return new HttpMessage("400",e.getMessage());
    }

    private HttpMessage validForParam(BindingResult bindingResult) {
        HttpMessage httpMessage = new HttpMessage();
        if(bindingResult.hasErrors()) {
            httpMessage.setCode("415");
            httpMessage.setMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return httpMessage;
        }
        return null;
    }
}
