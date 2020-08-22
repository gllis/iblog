package com.gllis.iblog.controller;

import com.gllis.iblog.annotation.ReqMapper;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.model.db.Tag;
import com.gllis.iblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 标签控制器
 *
 * @author GL
 * @created 2019/5/31.
 */
@RestController
@RequestMapping({"/tag/", "/admin/tag/"})
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 保存标签
     *
     * @param tag
     * @return
     */
    @PostMapping("save")
    public Mono<Result> save(@RequestBody Tag tag) {
        return tagService.save(tag);
    }

    /**
     * 移除标签
     *
     * @param id
     * @return
     */
    @GetMapping("remove")
    public Mono<Result> remove(String id) {
        return tagService.remove(id);
    }


    /**
     * 标签列表
     *
     * @return
     */
    @ReqMapper("list")
    public Mono<Result> list() {
        return tagService.getAll();
    }
}
