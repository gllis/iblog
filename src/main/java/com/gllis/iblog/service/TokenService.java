package com.gllis.iblog.service;

import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.Token;
import com.gllis.iblog.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * token 管理
 *
 * @author GL
 * @created 2019/6/18.
 */
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * 获取指定key的token
     *
     * @param key
     * @return
     */
    public Mono<Token> get(String key) {
        return tokenRepository.findById(key);
    }

    /**
     * 保存
     *
     * @param token
     * @return
     */
    public Mono<Result> save(Token token) {
        return tokenRepository.save(token).flatMap(t -> Mono.just(new Result()));
    }

}
