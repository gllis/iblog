package com.gllis.iblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 分类
 *
 * @author GL
 * @created 2019/5/31.
 */
@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    @Id
    private String id;
    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date created;

    public Category() {
        this.created = new Date();
    }
}
