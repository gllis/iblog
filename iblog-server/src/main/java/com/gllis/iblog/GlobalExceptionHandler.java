package com.gllis.iblog;

import com.gllis.iblog.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author GL
 * @created 2020/8/23.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Object exception(Exception e) throws Exception {
        e.printStackTrace();

        return new Result(-1, null, e.getMessage());
    }
}
