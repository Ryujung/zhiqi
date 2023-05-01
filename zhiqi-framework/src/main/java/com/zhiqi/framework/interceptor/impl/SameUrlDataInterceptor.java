package com.zhiqi.framework.interceptor.impl;

import com.alibaba.fastjson.JSONObject;
import com.zhiqi.common.annotation.RepeatSubmit;
import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.core.redis.RedisCache;
import com.zhiqi.common.filter.RepeatableReadRequestWrapper;
import com.zhiqi.common.utils.StringUtils;
import com.zhiqi.common.utils.http.HttpHelper;
import com.zhiqi.framework.interceptor.AbstractRepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 通过请求路径和参数来判断是否为重复提交
 * 该方案会在redis中短暂的保存请求的参数和请求时间
 * 性能敏感的场景慎用，高并发场景推荐使用唯一token来保证幂等
 *
 * @author RyuJung
 * @since 2023/4/29-23:23
 */
@Component
public class SameUrlDataInterceptor extends AbstractRepeatSubmitInterceptor {

    public static final String REPEAT_PARAMS_KEY = "repeatParams";
    public static final String REPEAT_TIME_KEY = "repeatTime";

    @Value("${jwt.headerTokenName}")
    private String authHeader;

    @Autowired
    private RedisCache redisCache;

    /**
     * 通过判断请求的请求体和路径、请求间隔来判断是否为一个重复请求
     *
     * 如果不是重复请求，则根据注解的间隔要求，将当前用户（或路径）的请求参数和请求时间timestamp保存到redis中
     *
     * 过期时间即为注解要求的间隔
     *
     * @param request    当前请求
     * @param annotation 请求携带的 {@link RepeatSubmit} 注解
     */
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {
        String requestBodyString = null;
        if (request instanceof RepeatableReadRequestWrapper) {
            requestBodyString = HttpHelper.getRequestBodyString((RepeatableReadRequestWrapper) request);
        }
        if (StringUtils.isEmpty(requestBodyString)) {
            // 不能读取request的InputStream，否则会导致inputStream失效（只可读取一次）
            //！ 注意不能直接对request进行JSON解析，会报错
            requestBodyString = JSONObject.toJSONString(request.getParameterMap());
        }
        HashMap<String, Object> repeatDataMap = new HashMap<>(16);
        repeatDataMap.put(REPEAT_PARAMS_KEY, requestBodyString);
        repeatDataMap.put(REPEAT_TIME_KEY, System.currentTimeMillis());

        String uniqueKey = request.getHeader(authHeader);

        if (StringUtils.isEmpty(uniqueKey)) {
            // 如果没有认证请求头，就把当前请求的uri当做唯一键（是否欠妥） FIXME
            uniqueKey = request.getRequestURI();
        }

        // 重复请求前缀 + 用户的认证header作为redis的唯一键
        String redisKey = Constants.REPEAT_SUBMIT_PREFIX + uniqueKey;
        Map<String, Object> cacheMap = redisCache.getCacheMap(redisKey);
        if (StringUtils.isNotEmpty(cacheMap)) {
            return equalsRepeatData(repeatDataMap, cacheMap)
                    && isLessThanRepeatInterval(repeatDataMap, cacheMap, annotation.interval());
        }

        redisCache.setCacheMap(redisKey, repeatDataMap);
        redisCache.expire(redisKey, annotation.interval(), TimeUnit.MILLISECONDS);
        return false;
    }

    private boolean isLessThanRepeatInterval(HashMap<String, Object> repeatDataMap, Map<String, Object> cacheMap, int interval) {
        Long repeatTime = (Long) repeatDataMap.get(REPEAT_TIME_KEY);
        Long cachedTime = (Long) cacheMap.get(REPEAT_TIME_KEY);
        return Math.abs(repeatTime - cachedTime) < interval;
    }

    private boolean equalsRepeatData(HashMap<String, Object> repeatDataMap, Map<String, Object> cacheMap) {
        String repeatParams = (String) repeatDataMap.get(REPEAT_PARAMS_KEY);
        String cachedParams = (String) cacheMap.get(REPEAT_PARAMS_KEY);
        return StringUtils.equals(repeatParams, cachedParams);
    }

}
