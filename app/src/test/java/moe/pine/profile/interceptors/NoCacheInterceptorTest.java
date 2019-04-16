package moe.pine.profile.interceptors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NoCacheInterceptorTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private NoCacheInterceptor noCacheInterceptor;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        noCacheInterceptor = new NoCacheInterceptor();
    }

    @Test
    public void preHandleTest() {
        noCacheInterceptor.preHandle(request, response, null);

        final List<String> headers = response.getHeaders("Cache-Control");
        assertEquals(1, headers.size());

        final String header = response.getHeader("Cache-Control");
        assertEquals("private, no-cache, no-store, must-revalidate", header);
    }
}
