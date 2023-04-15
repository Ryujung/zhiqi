package com.ryujung.zhiqi.common.annotation;

import com.ryujung.zhiqi.common.utils.poi.ExcelHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

/**
 * @author RyuJung
 * @since 2023/4/15-10:42
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    /**
     * 导出时在excel中排序
     */
    int sort() default Integer.MAX_VALUE;

    /**
     * 导出到Excel中的名字.
     */
    String name() default "";

    /**
     * 日期格式, 如: yyyy-MM-dd
     */
    String dateFormat() default "";

    /**
     * 如果是字典类型，请设置字典的type值 (如: sys_user_sex)
     */
    String dictType() default "";

    /**
     * 读取内容转表达式 (如: 0=男,1=女,2=未知)
     */
    String readConverterExp() default "";

    /**
     * 分隔符，读取字符串组内容
     */
    String separator() default ",";

    /**
     * BigDecimal 精度 默认:-1(默认不开启BigDecimal格式化)
     */
    int scale() default -1;

    /**
     * BigDecimal 舍入规则 默认:BigDecimal.ROUND_HALF_EVEN
     */
    int roundingMode() default BigDecimal.ROUND_HALF_DOWN;

    ColumnType cellType() default ColumnType.STRING;

    double height() default 14;

    double width() default 16;

    String suffix() default "";

    String defaultValue() default "";

    String prompt() default "";

    String[] combo() default {};

    boolean isExport() default true;

    String targetAttr() default "";

    boolean isStatistics() default false;

    Align align() default Align.AUTO;

    Class<?> handle() default ExcelHandler.class;

    String[] args() default {};

    Type type() default Type.ALL;

    enum ColumnType {
        NUMERIC(0), STRING(1), IAMGE(2);
        private final int value;

        ColumnType(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }

    enum Align {
        AUTO(0), LEFT(1), CENTER(2), RIGHT(3);
        private final int value;

        Align(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

    }

    enum Type {
        ALL(0), EXPORT(1), IMPORT(2);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}
