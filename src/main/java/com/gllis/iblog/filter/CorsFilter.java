package com.gllis.iblog.filter;

import com.gllis.iblog.utils.HttpUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 跨域过滤器
 *
 * @author GL
 * @created 2019/6/11.
 */
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpUtils.setCorsParams(exchange);
        return chain.filter(exchange);
    }
}
