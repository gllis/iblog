package com.gllis.iblog.filter;


import com.gllis.iblog.model.Result;
import com.gllis.iblog.utils.ErrCode;
import com.gllis.iblog.utils.HttpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 登录失败 处理事件
 *
 * @author GL
 * @created 2018/3/12.
 */
@Component
public class LoginFailHandler implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException e) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        return HttpUtils.render(exchange, new Result(ErrCode.AUTH_FAIL, null, "用户名或密码错误！"));
    }
}
