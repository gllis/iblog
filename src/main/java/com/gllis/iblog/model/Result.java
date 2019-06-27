package com.gllis.iblog.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果 Model
 *
 * @author GL
 * @created 2019/5/27.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    /**
     * 错误码
     */
    private int errcode;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 返回数据
     */
    private Object data;

    private String errmsg = "ok";


    public Result(Object data) {
        this.data = data;
    }

    public Result(Integer total, Object data) {
        this.total = total;
        this.data = data;
    }

    public Result(Integer errcode, Object data, String errmsg) {
        this.errcode = errcode;
        this.data = data;
        this.errmsg = errmsg;
    }
}
