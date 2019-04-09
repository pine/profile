package moe.pine.profile.filters;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CompressedServletResponseWrapper
    extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream outputStream;
    private final ServletOutputStream servletOutputStream;
    private final PrintWriter printWriter;

    public CompressedServletResponseWrapper(
        HttpServletResponse response
    ) {
        super(response);

        outputStream = new ByteArrayOutputStream(response.getBufferSize());
        servletOutputStream = new ServletOutputStream() {
            @Override
            public void close() throws IOException {
                outputStream.close();
            }

            @Override
            public void flush() throws IOException {
                outputStream.flush();
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {
            }

            @Override
            public void write(int b) {
                outputStream.write(b);
            }
        };
        printWriter = new PrintWriter(
            new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return servletOutputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return printWriter;
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();

        servletOutputStream.flush();
        printWriter.flush();
    }

    public String getContent() {
        return outputStream.toString(StandardCharsets.UTF_8);
    }

    public byte[] getContentBytes() {
        return outputStream.toByteArray();
    }
}
