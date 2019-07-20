package com.zte.zudp.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zte.zudp.common.exception.BaseException;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-17.
 */
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static String[] parsePatterns = {
            "yyyy", "yyyy-MM", "yyyy-MM-dd",
            "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss"
            , "yyyy-MM-dd HH:mm:ss.SSS", "HH:mm:ss.SSS"};

    private static ThreadLocal<SimpleDateFormat> timeThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<SimpleDateFormat> defautThreadLocal = new ThreadLocal<>();

    /**
     * 获取对应日期格式对象
     *
     * @param format 日期格式
     */
    public static SimpleDateFormat getDateFormat(DateFormat format) {
        return switchDateFormat(parsePatterns[format.ordinal()]);
    }

    public static SimpleDateFormat getDateFormat(String formatStr) {
        return switchDateFormat(formatStr);
    }

    private static SimpleDateFormat switchDateFormat(String formatStr) {
        if (StringUtils.equals(formatStr, parsePatterns[DateFormat.TIME.ordinal()])) {
            SimpleDateFormat df = timeThreadLocal.get();
            if (df == null) {
                df = new SimpleDateFormat(parsePatterns[DateFormat.TIME.ordinal()]);
                timeThreadLocal.set(df);
            }
            return df;
        } else if (StringUtils.equals(formatStr, parsePatterns[DateFormat.SECOND.ordinal()])) {
            SimpleDateFormat df = defautThreadLocal.get();
            if (df == null) {
                df = new SimpleDateFormat(parsePatterns[DateFormat.TIME.ordinal()]);
                defautThreadLocal.set(df);
            }
            return df;
        } else {
            return new SimpleDateFormat(formatStr);
        }
    }

    public static String format(Date date, String formatStr) throws ParseException {
        return getDateFormat(formatStr).format(date);
    }

    public static String format(Date date, DateFormat format) {
        return getDateFormat(format).format(date);
    }

    /**
     * 通过默认的日期格式格式化日期
     */
    public static String formatDefault(Date date) {
        return getDateFormat(DateFormat.SECOND).format(date);
    }

    /**
     * 格式化当前日期
     */
    public static String formatNow(DateFormat format) {
        return format(new Date(), format);
    }

    /**
     * 格式化当前日期
     */
    public static String formatNow(String formatStr) throws ParseException {
        return format(new Date(), formatStr);
    }

    public static Date parse(String str, String parseStr) throws ParseException {
        return getDateFormat(parseStr).parse(str);
    }

    public static Date parse(String str, DateFormat format) {
        try {
            return getDateFormat(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BaseException("common.DateUtils", e.getMessage());
        }
    }

    /**
     * 通过默认的日期格式解析字符串
     */
    public static Date parseDefault(String str) {
        try {
            return getDateFormat(DateFormat.SECOND).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new BaseException("common.DateUtils", e.getMessage());
        }
    }
    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 解析字符串为 Date 类型数据
     */
    public static Date parseDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(str, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    public enum DateFormat {
        YEAR,
        MONTH,
        DAY,
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND,
        TIME
    }
}
