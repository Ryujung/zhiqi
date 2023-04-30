package com.zhiqi.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zhiqi.common.annotation.RepeatSubmit;
import com.zhiqi.common.core.domain.CommonResult;
import com.zhiqi.common.utils.ServletUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 防止表单重复提交的拦截器
 *
 * @author RyuJung
 * @since 2023/4/29-23:10
 */
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (isRepeatSubmit(request, annotation)) {
                    CommonResult errorResult = CommonResult.error(annotation.message());
                    ServletUtils.renderString(response, JSONObject.toJSONString(errorResult));
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证是否重复提交，已知方案有：
     * 1. 请求地址+参数对比校验（当前使用）
     * 2. 每次请求需携带唯一token校验
     *
     * @param request    当前请求
     * @param annotation 请求携带的 {@link RepeatSubmit} 注解
     * @return true 重复请求，将被拦截； false 正常请求，将正常处理
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);

}
