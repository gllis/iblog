package com.gllis.iblog.service;

import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.db.SysConfig;
import com.gllis.iblog.repository.SysConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 系统配置
 *
 * @author GL
 * @created 2020/8/22.
 */
@Service
public class SysConfigService {

    @Autowired
    private SysConfigRepository sysConfigRepository;


    /**
     * 获取指定系统配置
     *
     * @param key
     * @return
     */
    public Mono<Result> get(String key) {
        return sysConfigRepository.findById(key).flatMap(data -> {
            if (data == null) {
                return Mono.just(new Result());
            } else {
                return Mono.just(new Result(data.getValue()));
            }
        });
    }


    /**
     * 保存系统配置
     *
     * @param sysConfig
     * @return
     */
    public Mono<Result> save(SysConfig sysConfig) {
        return sysConfigRepository.save(sysConfig)
                .flatMap(t -> Mono.just(new Result()));
    }

}
