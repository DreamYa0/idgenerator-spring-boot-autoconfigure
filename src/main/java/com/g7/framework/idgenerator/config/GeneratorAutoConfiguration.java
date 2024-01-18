package com.g7.framework.idgenerator.config;

import com.g7.framework.idgenerator.DefaultUidGenerator;
import com.g7.framework.idgenerator.SnowflakeIdGenerator;
import com.g7.framework.idgenerator.UidGenerator;
import com.g7.framework.idgenerator.worker.DisposableWorkerIdAssigner;
import com.g7.framework.redisson.configuration.RedissonConfiguration;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dreamyao
 * @title
 * @date 2018/11/1 10:28 AM
 * @since 1.0.0
 */
@Configuration
@AutoConfigureAfter(value = RedissonConfiguration.class)
public class GeneratorAutoConfiguration {

    @Bean
    @ConditionalOnBean(value = RedissonClient.class)
    public SnowflakeIdGenerator snowflakeIdGenerator(@Autowired UidGenerator uidGenerator) {
        return new SnowflakeIdGenerator(uidGenerator);
    }

    @Bean
    @ConditionalOnBean(value = RedissonClient.class)
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Bean
    @ConditionalOnBean(value = {RedissonClient.class,DisposableWorkerIdAssigner.class})
    public DefaultUidGenerator cachedUidGenerator(@Autowired DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
        DefaultUidGenerator generator = new DefaultUidGenerator();
        // 用完即弃的WorkerIdAssigner, 依赖Redis操作
        generator.setWorkerIdAssigner(disposableWorkerIdAssigner);

        generator.setTimeBits(28);
        generator.setWorkerBits(22);
        generator.setSeqBits(13);
        generator.setEpochStr("2020-01-20");

        return generator;
    }
}
