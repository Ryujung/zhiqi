package com.zhiqi.common.utils;

import com.zhiqi.common.core.domain.entity.SysDictData;
import com.zhiqi.common.core.text.StrFormatter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author RyuJung
 * @since 2023/4/14-19:30
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final char SEPARATOR = '_';
    public static final String EMPTY_STR = "";

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    public static boolean isEmpty(String[] strArr) {
        return isNull(strArr) || strArr.length == 0;
    }

    public static boolean isNotEmpty(String[] strArr) {
        return !isEmpty(strArr);
    }

    public static boolean isEmpty(Object[] objArr) {
        return isNull(objArr) || objArr.length == 0;
    }

    public static boolean isNotEmpty(Object[] objArr) {
        return !isEmpty(objArr);
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || EMPTY_STR.equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll)
    {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll)
    {
        return !isEmpty(coll);
    }

    /**
     * 驼峰转下划线命名
     */
    public static String toUnderScoreCase(String str) {
        if (isEmpty(str)) {
            return str;
        }

        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 1);
        boolean wasPrevTranslated = false;
        int i = 0;
        if (str.charAt(0) == SEPARATOR) {
            sb.append(SEPARATOR);
            i++;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c == SEPARATOR) {
                sb.append(SEPARATOR);
                wasPrevTranslated = true;
            } else if (Character.isUpperCase(c)) {
                if (!wasPrevTranslated && sb.length() > 0 && sb.charAt(sb.length() - 1) != SEPARATOR) {
                    sb.append(SEPARATOR);
                }
                sb.append(Character.toLowerCase(c));
                wasPrevTranslated = true;
            } else {
                sb.append(c);
                wasPrevTranslated = false;
            }
        }
        return sb.toString();
    }

    public static String format(String template, Object... params) {
        if (isEmpty(params) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String columnName) {
        if (isNull(columnName)) {
            return null;
        }
        columnName = columnName.toLowerCase();
        StringBuilder sb = new StringBuilder(columnName.length());
        boolean isUpperCase = false;
        for (int i = 0; i < columnName.length(); i++) {
            char c = columnName.charAt(i);
            if (c == SEPARATOR) {
                isUpperCase = true;
            } else if (isUpperCase) {
                sb.append(Character.toUpperCase(c));
                isUpperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj)
    {
        return (T) obj;
    }
}
