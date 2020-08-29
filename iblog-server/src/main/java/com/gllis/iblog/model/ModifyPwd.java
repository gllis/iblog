package com.gllis.iblog.model;

import lombok.Data;

/**
 * 修改密码请求实体
 *
 * @author GL
 * @created 2019/7/10.
 */
@Data
public class ModifyPwd {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 旧密码
     */
    private String oldPwd;
    /**
     * 新密码
     */
    private String newPwd;
}
