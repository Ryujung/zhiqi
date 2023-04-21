package com.zhiqi.generator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author RyuJung
 * @since 2023/4/15-11:49
 */
@Configuration
@PropertySource("classpath:generator.yml")
@ConfigurationProperties(prefix = "gen")
public class GenConfig {

    /**
     * 作者
     */
    private static String author;

    /**
     * 生成包路径
     */
    private static String packageName;

    /**
     * 自动去除表前缀，默认是false
     */
    private static boolean autoRemovePre;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    private static String tablePrefix;

    public static String getAuthor() {
        return author;
    }

    @Value("${author}")
    public void setAuthor(String author) {
        GenConfig.author = author;
    }

    public static String getPackageName() {
        return packageName;
    }

    @Value("${packageName}")
    public void setPackageName(String packageName) {
        GenConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre() {
        return autoRemovePre;
    }

    @Value("${autoRemovePre}")
    public void setAutoRemovePre(boolean autoRemovePre) {
        GenConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix() {
        return tablePrefix;
    }

    @Value("${tablePrefix}")
    public void setTablePrefix(String tablePrefix) {
        GenConfig.tablePrefix = tablePrefix;
    }
}
