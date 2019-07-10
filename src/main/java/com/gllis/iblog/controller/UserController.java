package com.gllis.iblog.controller;

import com.gllis.iblog.model.ModifyPwd;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.User;
import com.gllis.iblog.repository.UserRepository;
import com.gllis.iblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * 用户控制器
 *
 * @author GL
 * @created 2019/5/27.
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("save")
    public Mono<Result> save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    @PostMapping("modifyPwd")
    public Mono<Result> modifyPwd(@RequestBody ModifyPwd req) {
        return userService.modifyPwd(req);
    }


}
