package com.zhiqi.common.annotation;

import com.zhiqi.common.enums.BusinessType;
import com.zhiqi.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author RyuJung
 * @since 2023/4/25-17:00
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 业务类型
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作类型
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应参数
     */
    boolean isSaveResponseData() default true;

}
