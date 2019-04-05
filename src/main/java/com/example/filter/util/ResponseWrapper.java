package com.example.filter.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// 获取response返回值
public class ResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream buffer;

    private ServletOutputStream out;

    public ResponseWrapper(HttpServletResponse httpServletResponse) throws IOException {
        super(httpServletResponse);
        buffer = new ByteArrayOutputStream();
        out = new WrapperOutputStream(buffer);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return out;
    }

    @Override
    public void flushBuffer()
            throws IOException {
        if (out!=null) {
            out.flush();
        }
    }

    public byte[] getContent()
            throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

}
