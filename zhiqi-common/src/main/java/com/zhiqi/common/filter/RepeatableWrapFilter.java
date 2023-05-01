package com.zhiqi.common.filter;

import com.zhiqi.common.utils.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 通过过滤器将JSON格式的HttpServletRequest请求转换为可重复读取InputStream的请求
 *
 * @author RyuJung
 * @since 2023/5/1-11:50
 */
public class RepeatableWrapFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 只对HttpServletRequest并且是JSON格式的请求进行处理
        if (!(request instanceof HttpServletRequest) ||
                StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            filterChain.doFilter(request, response);
            return;
        }

        RepeatableReadRequestWrapper requestWrapper = new RepeatableReadRequestWrapper((HttpServletRequest) request);
        filterChain.doFilter(requestWrapper, response);
    }
}
