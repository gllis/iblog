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
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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

    private SecurityContext context;

    @Autowired
    private TokenService tokenService;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        Authentication authentication = context.getAuthentication();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new SimpleGrantedAuthority("ROLE_ADMIN") );
        authentication = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getAuthorities(), authorities);

        context.setAuthentication(authentication);
        this.context = context;

        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token != null) {
            String username = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return tokenService.get(username).flatMap(_token -> {
                System.out.println(token);
                System.out.println(_token);
                System.out.println(token.equals(_token.getValue()));
                if (_token == null || !token.equals(_token.getValue())) {
                    return Mono.empty();
                } else {
                    return Mono.justOrEmpty(context);
                }
            });

        }
        return Mono.empty();
    }
}