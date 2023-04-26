package com.zhiqi.common.annotation;

import com.zhiqi.common.contant.Constants;
import com.zhiqi.common.enums.LimitType;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @author RyuJung
 * @since 2023/4/26-15:06
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * 限流key
     */
    String key() default Constants.RATE_LIMIT_KEY;

    /**
     * 限流时间,单位秒
     */
    int time() default 60;

    /**
     * 限流次数
     */
    int count() default 100;

    /**
     * 限流类型
     */
    LimitType limitType() default LimitType.DEFAULT;
}
