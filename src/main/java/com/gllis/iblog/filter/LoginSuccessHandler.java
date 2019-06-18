package com.gllis.iblog.filter;


import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.Token;
import com.gllis.iblog.service.TokenService;
import com.gllis.iblog.service.UserService;
import com.gllis.iblog.utils.CacheConst;
import com.gllis.iblog.utils.ErrCode;
import com.gllis.iblog.utils.HttpUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * 登录成功 处理事件
 *
 * @author GL
 * @created 2018/3/12.
 */
@Slf4j
@Component
public class LoginSuccessHandler implements ServerAuthenticationSuccessHandler {


    @Value("${jwt.key}")
    private String jwtKey;


    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + CacheConst.TIME_OUT_ONE_DAY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtKey) //采用HS512算法
                .compact();

        webFilterExchange.getExchange().getResponse().getHeaders().add("Authorization", token);

        tokenService.save(new Token(authentication.getName(), token));

        return userService.getByName(authentication.getName()).flatMap(user -> {
            if (user != null) {
                return tokenService.save(new Token(authentication.getName(), token)).flatMap( t -> HttpUtils.render(exchange, new Result(user)));
            } else {
                return HttpUtils.render(exchange, new Result(ErrCode.USER_NOT_FOUND, null));
            }
        });

    }
}
