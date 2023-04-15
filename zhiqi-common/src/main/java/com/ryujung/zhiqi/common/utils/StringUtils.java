package com.ryujung.zhiqi.common.utils;

import com.ryujung.zhiqi.common.core.text.StrFormatter;
import com.sun.javafx.binding.StringFormatter;

/**
 * @author RyuJung
 * @since 2023/4/14-19:30
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final char SEPARATOR = '_';
    public static final String EMPTY_STR = "";

    private static boolean isNull(Object object) {
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
}
