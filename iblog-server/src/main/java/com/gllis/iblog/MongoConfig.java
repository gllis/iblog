package com.gllis.iblog;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;


/**
 * Mongo DB 配置
 * 去掉 _class 生成
 *
 * @author GL
 * @created 2019/6/5.
 */
@Configuration
public class MongoConfig implements InitializingBean {

    @Autowired
    @Lazy
    private MappingMongoConverter mappingMongoConverter;

    @Override
    public void afterPropertiesSet() throws Exception {
        // Don't save _class to mongo
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

}
