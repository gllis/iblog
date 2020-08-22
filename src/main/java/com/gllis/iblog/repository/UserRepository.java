package com.gllis.iblog.repository;

import com.gllis.iblog.model.db.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 用户 Repository
 *
 * @author GL
 * @created 2019/5/27.
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {


    @Query("{}")
    Flux<User> findAll(Pageable pageable);

    /**
     * 查找指定用户名用户信息
     *
     * @param name
     * @return
     */
    @Query("{'name' : ?0}")
    Mono<User> findByName(String name);
}
