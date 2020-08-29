package com.gllis.iblog.service;

import com.gllis.iblog.model.ModifyPwd;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.db.User;
import com.gllis.iblog.repository.UserRepository;
import com.gllis.iblog.utils.ErrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
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

    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    public Mono<Result> modifyPwd(ModifyPwd req) {
        return userRepository.findById(req.getUserId()).flatMap(data -> {
            String password = DigestUtils.md5DigestAsHex(req.getOldPwd().getBytes());
            if (!password.equals(data.getPassword())) {
                return Mono.just(new Result(ErrCode.AUTH_FAIL, null, ErrCode.AUTH_FAIL_MSG));
            }
            data.setPassword(DigestUtils.md5DigestAsHex(req.getNewPwd().getBytes()));
            return userRepository.save(data).flatMap(t -> Mono.just(new Result()));
        });
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    public Mono<Result> save(User user) {
        return userRepository.save(user).flatMap(t -> Mono.just(new Result(t)));
    }
}
