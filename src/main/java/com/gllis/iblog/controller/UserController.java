package com.gllis.iblog.controller;

import com.gllis.iblog.model.User;
import com.gllis.iblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository userRepository;

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("save")
    public Mono<User> save(@RequestBody User user) {
        return userRepository.save(user);
    }


}
