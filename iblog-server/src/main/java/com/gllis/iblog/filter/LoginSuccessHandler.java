package com.gllis.iblog.filter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

        // 生成token
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + CacheConst.TIME_OUT_ONE_DAY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtKey) //采用HS512算法
                .compact();

        webFilterExchange.getExchange().getResponse().getHeaders().add("Authorization", token);

        return userService.getByName(authentication.getName()).flatMap(user -> {
            if (user != null) {
                JSONObject jo = JSON.parseObject(JSON.toJSONString(user));
                // remove password
                jo.remove("password");
                // 将token保存起来，并返回用户信息
                return tokenService.save(new Token(authentication.getName(), token, user.getRole())).flatMap( t -> HttpUtils.render(exchange, new Result(jo)));
            } else {
                // 找不到用户返回错误码
                return HttpUtils.render(exchange, new Result(ErrCode.USER_NOT_FOUND, null));
            }
        });

    }
}
