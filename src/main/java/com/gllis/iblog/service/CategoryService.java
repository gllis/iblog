package com.gllis.iblog.service;

import com.gllis.iblog.model.Category;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 分类 业务逻辑
 *
 * @author GL
 * @created 2019/5/31.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * 保存
     *
     * @param category
     * @return
     */
    public Mono<Result> save(Category category) {
        return categoryRepository.save(category)
                .flatMap(t -> Mono.just(new Result()));
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Mono<Result> remove(String id) {
        return categoryRepository.removeById(id)
                .flatMap(t -> Mono.just(new Result()));
    }

    /**
     * 获取所有标签
     *
     * @return
     */
    public Mono<Result> getAll() {
        return categoryRepository.findAll()
                .collectList()
                .flatMap(data -> Mono.just(new Result(data)));
    }

}
