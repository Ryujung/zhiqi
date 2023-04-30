package com.zhiqi.common.filter;

import org.apache.commons.compress.utils.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 解决请求中InputStream无法重复获取的问题
 *
 * 使用装饰器模式对请求进行包装加强（某种程度上），
 *
 * 需要注意的是，使用该wrapper可能会占用大量的内存，
 * 因为它会将整个input stream都缓存到内存中。
 * 因此，需要根据实际情况，权衡使用该wrapper的利弊。
 *
 * @author RyuJung
 * @since 2023/4/30-22:58
 */
public class RepeatableReadRequestWrapper extends HttpServletRequestWrapper {

    private final ByteArrayOutputStream cachedBytes;

    public RepeatableReadRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        request.setCharacterEncoding("UTF-8");

        // 将request中的input stream缓存到字节数组输出流中
        cachedBytes = new ByteArrayOutputStream();
        IOUtils.copy(request.getInputStream(), cachedBytes);
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 创建一个新的输入流，用于返回给调用者，该输入流包装了字节数组输出流
        return new CachedServletInputStream();
    }

    private class CachedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream input;

        public CachedServletInputStream() {
            input = new ByteArrayInputStream(cachedBytes.toByteArray());
        }

        @Override
        public int read() throws IOException {
            return input.read();
        }

        @Override
        public boolean isFinished() {
            return input.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException();
        }
    }
}

