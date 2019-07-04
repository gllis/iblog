package com.gllis.iblog.service;

import com.gllis.iblog.model.Article;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * 文章 业务逻辑
 *
 * @author GL
 * @created 2019/5/31.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 保存
     *
     * @param article
     * @return
     */
    public Mono<Result> save(Article article) {
        return articleRepository.save(article)
                .flatMap(t -> Mono.just(new Result()));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Mono<Result> remove(String id) {
        return articleRepository.removeById(id)
                .flatMap(t -> Mono.just(new Result()));
    }

    /**
     * 获取所有文章 分页
     *
     * @return
     */
    public Mono<Result> getAll(String tag, Pageable pageable) {

        if (!StringUtils.isEmpty(tag)) {
            return articleRepository.count(tag).flatMap(c ->
                    articleRepository.find(tag, pageable).collectList().flatMap(data
                            -> Mono.just(new Result(c.intValue(), data)))
            );
        } else {
            return articleRepository.count().flatMap(c ->
                    articleRepository.find(pageable).collectList().flatMap(data
                            -> Mono.just(new Result(c.intValue(), data)))
            );
        }
    }


    /**
     * 获取指定文章
     *
     * @param id
     * @return
     */
    public Mono<Result> get(String id) {
        return articleRepository.findById(id).flatMap(data -> Mono.just(new Result(data)));
    }
}
