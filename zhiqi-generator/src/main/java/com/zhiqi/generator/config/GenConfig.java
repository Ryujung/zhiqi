package com.zhiqi.generator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author RyuJung
 * @since 2023/4/15-11:49
 */
@Component
@ConfigurationProperties(prefix = "gen")
@PropertySource("classpath:generator.yml")
public class GenConfig {

    private static String author;
    private static String packageName;
    private static boolean autoRemovePre;
    private static String tablePrefix;

    public static String getAuthor() {
        return author;
    }

    public static void setAuthor(String author) {
        GenConfig.author = author;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String packageName) {
        GenConfig.packageName = packageName;
    }

    public static boolean getAutoRemovePre() {
        return autoRemovePre;
    }

    public static void setAutoRemovePre(boolean autoRemovePre) {
        GenConfig.autoRemovePre = autoRemovePre;
    }

    public static String getTablePrefix() {
        return tablePrefix;
    }

    public static void setTablePrefix(String tablePrefix) {
        GenConfig.tablePrefix = tablePrefix;
    }
}
