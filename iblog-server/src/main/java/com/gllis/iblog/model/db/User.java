package com.gllis.iblog.model.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 用户
 *
 * @author GL
 * @created 2019/5/27.
 */
@Data
@Document
public class User {

    @Id
    private String id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 角色
     */
    private String role = "ADMIN";

}
