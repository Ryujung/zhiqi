package com.zhiqi.framework.interceptor.impl;

import com.zhiqi.common.annotation.RepeatSubmit;
import com.zhiqi.framework.interceptor.RepeatSubmitInterceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author RyuJung
 * @since 2023/4/29-23:23
 */
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {

    /**
     * 为了将
     * @param request    当前请求
     * @param annotation 请求携带的 {@link RepeatSubmit} 注解
     * @return
     */
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {

        return false;
    }

}
