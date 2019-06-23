package moe.pine.profile.config;

import moe.pine.profile.properties.AppProperties;
import moe.pine.spring.cache.interceptors.CacheInterceptor;
import moe.pine.spring.cache.interceptors.CachePolicy;
import moe.pine.spring.cache.interceptors.CachePolicyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    @Bean
    public CacheInterceptor cacheInterceptor(
        final AppProperties appProperties
    ) {
        final long maxAge = appProperties.getPage().getMaxAge();
        final CachePolicy cachePolicy =
            new CachePolicyBuilder()
                .public_()
                .maxAge(maxAge)
                .build();

        return new CacheInterceptor(cachePolicy);
    }

    @Bean
    public CacheInterceptor noCacheInterceptor() {
        final CachePolicy cachePolicy =
            new CachePolicyBuilder()
                .private_()
                .noCache()
                .mustRevalidate()
                .noStore()
                .build();

        return new CacheInterceptor(cachePolicy);
    }
}
