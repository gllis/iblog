package com.gllis.iblog.controller;

import com.gllis.iblog.annotation.ReqMapper;
import com.gllis.iblog.model.Article;
import com.gllis.iblog.model.PageVo;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 文章控制器
 *
 * @author GL
 * @created 2019/5/31.
 */
@RestController
@RequestMapping({"/article/", "/admin/article/"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 保存文章
     *
     * @param article
     * @return
     */
    @PostMapping("save")
    public Mono<Result> save(@RequestBody Article article) {
        return articleService.save(article);
    }

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    @GetMapping("remove")
    public Mono<Result> remove(String id) {
        return articleService.remove(id);
    }

    /**
     * 文章列表
     *
     * @return
     */
    @ReqMapper("list")
    public Mono<Result> list(@RequestBody PageVo<Article> pageVo) {
        Pageable pageable = PageRequest.of(pageVo.getPage(), pageVo.getSize());
        return articleService.getAll(pageVo.getObj(), pageable);
    }

    @GetMapping("get")
    public Mono<Result> get(String id) {
        return articleService.get(id);
    }

}
