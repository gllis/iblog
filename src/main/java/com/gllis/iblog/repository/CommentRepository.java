package com.gllis.iblog.repository;


import com.gllis.iblog.model.db.Comment;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 评论 Repository
 *
 * @author GL
 * @created 2019/7/4.
 */
@Repository
public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    /**
     * 按文章ID查换评论
     *
     * @param articleId
     * @return
     */
    @Query(value = "{'article_id' : ?0}", sort = "{ _id : -1 }")
    Flux<Comment> find(String articleId);

}
