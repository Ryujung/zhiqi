package com.zhiqi.common.utils;

import com.zhiqi.common.core.text.Converter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet相关工具类
 *
 * @author RyuJung
 * @since 2023/4/14-23:44
 */
public class ServletUtils {

    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    public static String getParameter(String name, String defaultValue) {
        return Converter.toStr(getParameter(name), defaultValue);
    }

    public static Integer getParameterToInt(String name) {
        return Converter.toInt(getParameter(name));
    }

    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Converter.toInt(getParameter(name), defaultValue);
    }

    public static Boolean getParameterToBool(String name) {
        return Converter.toBool(getParameter(name));
    }

    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        return Converter.toBool(getParameter(name), defaultValue);
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param str      待渲染的字符串
     * @return null
     */
    public static void renderString(HttpServletResponse response, String str) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
