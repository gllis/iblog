package com.gllis.iblog.service;


import com.gllis.iblog.model.Comment;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 评论 业务逻辑
 *
 * @author GL
 * @created 2019/7/4.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 查找指定文章所有评论
     *
     * @param articleId
     * @return
     */
    public Mono<Result> getAll(String articleId) {
        return commentRepository.find(articleId).collectList().flatMap(data -> Mono.just(new Result(data)));
    }

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    public Mono<Result> save(Comment comment) {
        return commentRepository.save(comment)
                .flatMap(t -> Mono.just(new Result()));
    }
}
