package moe.pine.profile.filters;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class HtmlCompressorFilter implements Filter {
    private final HtmlCompressor htmlCompressor;

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        if (!(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        final var httpResponse = (HttpServletResponse) response;
        final var wrappedResponse = new CompressedServletResponseWrapper(httpResponse);
        chain.doFilter(request, wrappedResponse);

        final String contentType = wrappedResponse.getContentType();
        if (contentType.startsWith("text/html")) {
            final String content = wrappedResponse.getContent();
            final String compressedContent = htmlCompressor.compress(content);
            response.getWriter().write(compressedContent);
        } else {
            response.getOutputStream().write(wrappedResponse.getContentBytes());
        }
    }
}
