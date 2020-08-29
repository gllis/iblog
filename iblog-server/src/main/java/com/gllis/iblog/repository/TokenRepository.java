package com.gllis.iblog.repository;

import com.gllis.iblog.model.Token;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Token Repository
 *
 * @author GL
 * @created 2019/5/31.
 */
@Repository
public interface TokenRepository extends ReactiveMongoRepository<Token, String> {


}
