package com.gllis.iblog.model;

import lombok.Data;

/**
 * @author GL
 * @created 2019/6/6.
 */
@Data
public class PageVo<T> {

    private int page;
    private int size;

    private T obj;

}
