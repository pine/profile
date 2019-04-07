package moe.pine.profile.config;

import lombok.RequiredArgsConstructor;
import moe.pine.profile.interceptors.CacheInterceptor;
import moe.pine.profile.interceptors.NoCacheInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final CacheInterceptor cacheInterceptor;
    private final NoCacheInterceptor noCacheInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
            .addInterceptor(cacheInterceptor)
            .addPathPatterns("/*")
            .excludePathPatterns("/health");
        registry
            .addInterceptor(noCacheInterceptor)
            .addPathPatterns("/health");
    }
}

