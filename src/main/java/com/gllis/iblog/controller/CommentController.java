package com.gllis.iblog.controller;


import com.gllis.iblog.model.Comment;
import com.gllis.iblog.model.Result;
import com.gllis.iblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * 评论 控制器
 *
 * @author GL
 * @created 2019/7/4.
 */
@RestController
@RequestMapping("/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取指定文章下所有评论
     *
     * @param articleId
     * @return
     */
    @GetMapping("list")
    public Mono<Result> list(String articleId) {
        return commentService.getAll(articleId);
    }

    /**
     * 保存评论
     *
     * @param comment
     * @return
     */
    @PostMapping("save")
    public Mono<Result> save(@RequestBody Comment comment) {
        return commentService.save(comment);
    }
}
