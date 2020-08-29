package com.gllis.iblog.model.db;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统配置
 *
 * @author GL
 * @created 2020/8/22.
 */
@Data
@Document(value = "sys_config")
public class SysConfig {

    /**
     * 配置key
     */
    @Id
    private String key;

    /**
     * 配置值
     */
    private Object value;
}
