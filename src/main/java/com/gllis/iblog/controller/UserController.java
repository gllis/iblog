package com.gllis.iblog.controller;

import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.User;
import com.gllis.iblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
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

    @RequestMapping("get")
    public Mono<Result> get(String id) {
        return userRepository.findById(id).flatMap(data -> Mono.just(new Result(data)));
    }

    @RequestMapping("save")
    public Mono<User> save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping("list")
    public Flux<User> list() {
        return userRepository.findAll();
    }

    @RequestMapping("list/page")
    public Mono<Result> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return userRepository.count().flatMap(c ->
                userRepository.findAll(pageable).collectList().flatMap(data
                        -> Mono.just(new Result(c.intValue(), data))));
    }


    @RequestMapping("users")
    public Mono<Result> user() {

        return userRepository.findAll().collectList().flatMap(data -> Mono.just(new Result(data)));
    }
}
