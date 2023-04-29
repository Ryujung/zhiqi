package com.zhiqi.common.enums;

/**
 * @author RyuJung
 * @since 2023/4/26-15:08
 */
public enum LimitType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP

}
