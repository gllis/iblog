package com.gllis.iblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 标签
 *
 * @author GL
 * @created 2019/5/31.
 */
@Data
@Document
public class Tag {

    @Id
    private String id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date created;

    public Tag() {
        this.created = new Date();
    }
}
