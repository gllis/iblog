package com.gllis.iblog.repository;

import com.gllis.iblog.model.db.SysConfig;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 系统配置 Repository
 *
 * @author GL
 * @created 2020/8/22.
 */
@Repository
public interface SysConfigRepository extends ReactiveMongoRepository<SysConfig, String> {


}
