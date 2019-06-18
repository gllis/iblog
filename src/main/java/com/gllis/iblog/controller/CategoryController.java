package com.gllis.iblog.controller;

import com.gllis.iblog.annotation.ReqMapper;
import com.gllis.iblog.model.Category;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 分类控制器
 *
 * @author GL
 * @created 2019/5/31.
 */
@RestController
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 保存分类
     *
     * @param category
     * @return
     */
    @PostMapping("save")
    public Mono<Result> save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    /**
     * 移除指定分类
     *
     * @param id
     * @return
     */
    @GetMapping("remove")
    public Mono<Result> remove(String id) {
        return categoryService.remove(id);
    }

    /**
     * 分类列表
     *
     * @return
     */
    @ReqMapper("list")
    public Mono<Result> list() {
        return categoryService.getAll();
    }
}
