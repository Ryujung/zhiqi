package com.zhiqi.common.contant;

import com.zhiqi.common.utils.spring.SpringUtils;

/**
 * @author RyuJung
 * @since 2023/4/16-16:25
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * 请求头中的token前缀，用于拼接token
     */
    public static final String REQUEST_HEADER_TOKEN_PREFIX = "Bearer ";

    /**
     * 登录用户保存在Claims中的token的字段名，用于从解析得到的Claims中，获取用户的token
     */
    public static final String LOGIN_USER_CLAIMS_TOKEN_NAME = "login_user_key";
    /**
     * 登录用户保存在redis中的token的Key的前缀，例如：“login_tokens:12345567”
     * 用于从Redis中获取登录用户的token值
     */
    public static final String LOGIN_USER_REDIS_KEY_PREFIX = "login_tokens:";

    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 自定义的线程池的名称，可以通过{@link SpringUtils#getBean(String)} 方法获取
     */
    public static final String THREAD_POOL_BEAN_NAME = "threadPoolExecutor";

    /**
     * 自定义的延迟任务线程池的名称，可以通过{@link SpringUtils#getBean(String)} 方法获取
     */
    public static final String SCHEDULED_THREAD_POOL_BEAN_NAME = "scheduledThreadPoolExecutor";
}
