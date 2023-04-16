package com.zhiqi.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author RyuJung
 * @since 2023/4/16-19:28
 */
public class DataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);
    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    public static final ThreadLocal<String> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        log.info("切换到{}数据源", dataSourceType);
        DATA_SOURCE_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return DATA_SOURCE_HOLDER.get();
    }

    public static void clear() {
        DATA_SOURCE_HOLDER.remove();
    }

}
