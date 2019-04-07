package moe.pine.profile.interceptors;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class NoCacheInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) {
        response.addHeader(HttpHeaders.PRAGMA, "no-cache");
        response.addHeader(
            HttpHeaders.CACHE_CONTROL, "private, no-cache, no-store, must-revalidate");
        return true;
    }
}
