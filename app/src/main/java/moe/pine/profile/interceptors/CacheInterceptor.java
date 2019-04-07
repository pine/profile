package moe.pine.profile.interceptors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class CacheInterceptor extends HandlerInterceptorAdapter {
    public final long maxAge;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        response.addHeader(
            HttpHeaders.CACHE_CONTROL, String.format("public, max-age=%d", maxAge));
        return true;
    }
}
