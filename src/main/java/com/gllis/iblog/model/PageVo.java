package com.gllis.iblog.model;

import lombok.Data;

/**
 * 分页 Vo
 *
 * @author GL
 * @created 2019/6/6.
 */
@Data
public class PageVo<T> {

    private int page;  // 第几页
    private int size;  // 每页数量

    private T obj;     // 分页数据实例

}
