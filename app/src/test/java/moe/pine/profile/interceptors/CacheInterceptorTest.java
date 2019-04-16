package moe.pine.profile.interceptors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CacheInterceptorTest {
    private static long MAX_AGE = 12345;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private CacheInterceptor cacheInterceptor;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        cacheInterceptor = new CacheInterceptor(MAX_AGE);
    }

    @Test
    public void preHandleTest() {
        cacheInterceptor.preHandle(request, response, null);

        final List<String> headers = response.getHeaders("Cache-Control");
        assertEquals(1, headers.size());

        final String header = response.getHeader("Cache-Control");
        assertEquals("public, max-age=" + MAX_AGE, header);
    }
}
