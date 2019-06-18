package com.gllis.iblog.service;

import com.gllis.iblog.model.User;
import com.gllis.iblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 用户业务逻辑
 *
 * @author GL
 * @created 2019/5/31.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 通过用户名查找用户
     *
     * @param name
     * @return
     */
    public Mono<User> getByName(String name) {
        return userRepository.findByName(name);
    }
}
