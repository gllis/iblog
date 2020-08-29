package com.gllis.iblog.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;


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
    private String role;
    @Indexed(name = "_created", expireAfterSeconds = 3600)
    private Date created;

    public Token(String key, String value, String role) {
        this.key = key;
        this.value = value;
        this.role = role;
        this.created = new Date();
    }
}
