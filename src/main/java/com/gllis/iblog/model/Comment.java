package com.gllis.iblog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 评论
 *
 * @author GL
 * @created 2019/7/4.
 */
@Data
@Document
public class Comment {

    @Id
    private String id;

    /**
     * 文章ID
     */
    private String article_id;

    /**
     * 昵称
     */
    private String nick_name;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String created;
}
