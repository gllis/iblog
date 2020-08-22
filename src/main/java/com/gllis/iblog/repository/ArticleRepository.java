package com.gllis.iblog.repository;

import com.gllis.iblog.model.db.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 文章 Repository
 *
 * @author GL
 * @created 2019/5/31.
 */
@Repository
public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {

    /**
     * 按标签查换文章
     *
     * @param tag
     * @param pageable
     * @return
     */
    @Query(value = "{'tag.id' : ?0}", sort = "{ _id : -1 }")
    Flux<Article> find(String tag, Pageable pageable);

    /**
     * 查找指定标签总条数
     *
     * @param tag
     * @return
     */
    @CountQuery( "{'tag.id' : ?0}")
    Mono<Long> count(String tag);

    /**
     * 查找所有文章
     *
     * @param pageable
     * @return
     */
    @Query(value = "{}", sort = "{ _id : -1 }")
    Flux<Article> find(Pageable pageable);

    /**
     * 删除指定文章
     *
     * @param id
     * @return
     */
    @DeleteQuery("{ '_id' : ?0 }")
    Mono<Long> removeById(String id);
}
