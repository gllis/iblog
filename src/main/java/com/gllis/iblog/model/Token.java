package com.gllis.iblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


/**
 * token
 *
 * @author GL
 * @created 2019/6/18.
 */
@Data
@NoArgsConstructor
public class Token {
    @Id
    private String key;
    private String value;

    public Token(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
