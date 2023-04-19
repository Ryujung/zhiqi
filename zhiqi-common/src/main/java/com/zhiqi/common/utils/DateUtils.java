package com.zhiqi.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author RyuJung
 * @since 2023/4/14-22:00
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    public static Date getNowDate() {
        return new Date();
    }

    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow(String format) {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(String format, String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return yyyy/MM/dd format date str
     */
    public static String datePath() {
        Date now = getNowDate();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * @return yyyyMMdd format date str
     */
    public static String dateTime() {
        Date now = getNowDate();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) throws ParseException {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(startTime);
    }

    /**
     * 计算两个时间差
     *
     * @return xx天xx小时xx分钟;
     */
    public static String getDatePoor(Date endDate, Date startDate) {
        long dayLong = 24 * 60 * 60 * 1000;
        long hourLong = 60 * 60 * 1000;
        long minuteLong = 60 * 1000;

        long diff = endDate.getTime() - startDate.getTime();
        long diffDay = 24 * 60 * 60 * 1000;
        long diffHour = diff % dayLong / hourLong;
        long diffMinute = diff % dayLong % hourLong / minuteLong;
        return diffDay + "天" + diffHour + "小时" + diffMinute + "分钟";
    }
}
