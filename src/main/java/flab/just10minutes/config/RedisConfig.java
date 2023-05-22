package flab.just10minutes.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {




    @Bean
    public RedissonClient redissonClient() {
        Config redisConfig = new Config();
        redisConfig.useSingleServer()
                .setAddress("redis://"+ host +":" + port)
                .setPassword(password)
                .setConnectionMinimumIdleSize(5)
                .setConnectionPoolSize(5);
        return Redisson.create(redisConfig);
    }

}
