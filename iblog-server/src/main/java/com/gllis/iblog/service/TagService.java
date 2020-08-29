package com.gllis.iblog.service;

import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.db.Tag;
import com.gllis.iblog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 标签 业务逻辑
 *
 * @author GL
 * @created 2019/5/31.
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 保存
     *
     * @param tag
     * @return
     */
    public Mono<Result> save(Tag tag) {
        return tagRepository.save(tag)
                .flatMap(t -> Mono.just(new Result()));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Mono<Result> remove(String id) {
        return tagRepository.removeById(id)
                .flatMap(t -> Mono.just(new Result()));
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    public Mono<Result> getAll() {
        return tagRepository.findAll()
                .collectList()
                .flatMap(data -> Mono.just(new Result(data)));
    }
}
