package com.gllis.iblog.security;


import com.gllis.iblog.service.TokenService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 上下文处理
 *
 * @author GL
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {


    @Value("${jwt.key}")
    private String jwtKey;

    @Autowired
    private TokenService tokenService;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {

        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token != null) {
            // 从jwt中获取用户名
            String username = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();


            // 从数据库中获取token进行对比成功返回鉴权信息
            return tokenService.get(username).flatMap(_token -> {
                if (_token == null || !token.equals(_token.getValue())) {
                    return Mono.empty();
                } else {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(_token.getRole()));

                    Authentication auth = new UsernamePasswordAuthenticationToken(username, token, authorities);
                    return Mono.justOrEmpty(new SecurityContextImpl(auth));
                }
            });

        }
        return Mono.empty();
    }
}