package com.gllis.iblog.filter;



import com.gllis.iblog.model.Result;
import com.gllis.iblog.utils.ErrCode;
import com.gllis.iblog.utils.HttpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 鉴权失败处理
 *
 * @author GL
 * @created 2018/3/12.
 */
@Component
public class UnauthorizedEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return HttpUtils.render(exchange, new Result(ErrCode.TOKEN_EXPIRE, null, "fail"));
    }
}
