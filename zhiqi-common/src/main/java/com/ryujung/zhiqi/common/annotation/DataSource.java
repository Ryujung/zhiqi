package com.ryujung.zhiqi.common.annotation;

import com.ryujung.zhiqi.common.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * 自定义多数据源切换注解
 *
 * 优先级：先方法，后类，如果方法覆盖了类上的数据源类型，以方法的为准，否则以类上的为准
 *
 * @author RyuJung
 * @since 2023/4/17-0:36
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {

    /**
     * 数据源切换
     */
    DataSourceType value() default DataSourceType.MASTER;
}
