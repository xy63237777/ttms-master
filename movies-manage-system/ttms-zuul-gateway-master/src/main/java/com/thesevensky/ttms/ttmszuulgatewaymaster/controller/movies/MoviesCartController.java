package com.thesevensky.ttms.ttmszuulgatewaymaster.controller.movies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.CartAndIdString;
import com.thesevensky.ttms.moviesmanageapi.commons.dto.TTMSPageInfo;
import com.thesevensky.ttms.moviesmanageapi.commons.http.HttpMessage;
import com.thesevensky.ttms.moviesmanageapi.constants.movies.mapping.MoviesCartConstants;
import com.thesevensky.ttms.moviesmanageapi.pojo.movies.MoviesCart;
import com.thesevensky.ttms.ttmszuulgatewaymaster.commons.ClientConstants;
import com.thesevensky.ttms.ttmszuulgatewaymaster.dto.MoviesCartAndStatus;
import com.thesevensky.ttms.ttmszuulgatewaymaster.service.movies.MoviesClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author TheSevenSky
 * @Date: 2019/6/1 14:42
 * @Version 1.0
 */
@RestController
@RequestMapping(MoviesCartConstants.PREFIX)
public class MoviesCartController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MoviesClientService moviesClientService = null;

    @GetMapping(ClientConstants.CLENT_PREFIX + MoviesCartConstants.MOVIES_CART_PAGE)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("返回当前用户的订单{num}代表分页")
    public TTMSPageInfo<MoviesCart> getMoviesCartList(@PathVariable("num") Integer num,
                                                      HttpServletRequest request) {
        return moviesClientService.getMoviesCartList(num,request.getHeader("Authorization"));
    }

    @PutMapping(ClientConstants.CLENT_PREFIX + MoviesCartConstants.MOVIES_CART_STATUS)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("修改订单的状态")
    public HttpMessage updateStatus(@RequestBody MoviesCartAndStatus moviesCartAndStatus,
                                    HttpServletRequest request) {
        return moviesClientService.updateStatus(moviesCartAndStatus.getMoviesCartId(),
                moviesCartAndStatus.getStatus(),request.getHeader("Authorization"));
    }

    @PostMapping(ClientConstants.CLENT_PREFIX + MoviesCartConstants.DEFAULT_MOVIES)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("生成订单")
    public HttpMessage insertMoviesCart(@RequestBody MoviesCart moviesCart,
                                        HttpServletRequest request) throws JsonProcessingException {
        System.out.println(moviesCart);
        System.out.println(request.getHeader("Authorization"));
        return moviesClientService.insertMoviesCart(objectMapper.writeValueAsString(moviesCart),
                request.getHeader("Authorization"));
    }

    @DeleteMapping(ClientConstants.CLENT_PREFIX + MoviesCartConstants.MOVIES_CART_DELETE)
    @PreAuthorize("isAuthenticated()")
    @ApiOperation("删除订单")
    public HttpMessage deleteMoviesCart(@PathVariable("moviesId") Long moviesId,
                                        HttpServletRequest request) {
        return moviesClientService.deleteMoviesCart(moviesId,request.getHeader("Authorization"));
    }
}
