package com.gllis.iblog.controller;

import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.db.SysConfig;
import com.gllis.iblog.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 系统配置 Controller
 *
 * @author GL
 * @created 2020/8/22.
 */
@RestController
@RequestMapping({"/sys/", "/admin/sys/"})
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;


    /**
     * 获取指定系统配置
     *
     * @param key
     * @return
     */
    @GetMapping("get")
    public Mono<Result> get(String key) {
        return sysConfigService.get(key);
    }


    /**
     * 保存系统配置
     *
     * @param sysConfig
     * @return
     */
    @PostMapping("save")
    public Mono<Result> save(@RequestBody SysConfig sysConfig) {
        return sysConfigService.save(sysConfig);
    }

}
