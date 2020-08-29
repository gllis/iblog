package com.gllis.iblog.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 文章
 *
 * @author GL
 * @created 2019/5/31.
 */
@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {

    @Id
    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 简介
     */
    private String summary;

    /**
     * 内容 markdown
     */
    private String content;

    /**
     * 内容 html
     */
    private String content_html;


    /**
     * 标签
     */
    @DBRef
    private Tag tag;


    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后修改时间
     */
    private Date modified;

    public Article() {
        this.created = new Date();
        this.modified = new Date();
    }
}
