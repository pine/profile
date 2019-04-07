package moe.pine.profile.config;

import moe.pine.profile.interceptors.CacheInterceptor;
import moe.pine.profile.properties.AppProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {
    private static final long MAX_AGE =
        TimeUnit.MINUTES.toSeconds(10); // 10 min

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public CacheInterceptor cacheInterceptor() {
        return new CacheInterceptor(MAX_AGE);
    }
}
