package com.gllis.iblog.repository;

import com.gllis.iblog.model.Category;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * 分类 Repository
 *
 * @author GL
 * @created 2019/5/31.
 */
@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

    /**
     * 删除指定ID分类
     *
     * @param id
     * @return
     */
    @DeleteQuery("{ '_id' : ?0 }")
    Mono<Long> removeById(String id);

}
