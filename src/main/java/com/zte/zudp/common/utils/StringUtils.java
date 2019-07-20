package com.zte.zudp.common.utils;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-14.
 */
public class StringUtils {

    /**
     * 去除参数前后的空格字符
     *
     * @param in .
     * @return 去除了前后空格字符的参数值
     */
    public static String trim(String in) {
        String out = in;

        if (out != null) {
            out = out.trim();

            if (out.equals("")) {
                out = null;
            }
        }

        return out;
    }

    /**
     * 将对象信息使用 [ 和 ] 包含
     *
     * @param msg 对象
     * @return 如果对象信息存在，则调用toString() 并将结果用中括号包裹，否则返回空字符串
     */
    public static String block(Object msg) {
        if (msg == null) {
            msg = "";
        }

        return "[" + msg.toString() + "]";
    }

    public static boolean equals(String a, String b) {
        if (a != null && a.equals(b)) {
            return true;
        }

        return b != null && b.equals(a);

    }

    /**
     * 获取字符串
     *
     * @return 如果字符串为null，则返回空字符串；否则返回其本身
     */
    public static String get(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 判断 字符序列 是否为空
     *
     * @param value 字符序列值
     * @return 如果字符序列为 @{code null} 或者 长度为0 返回 true，否则返回false
     */
    public static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    /**
     * 判断 字符序列 是否不为空
     *
     * @param value 字符序列值
     * @see StringUtils#isEmpty(CharSequence)
     */
    public static boolean isNotEmpty(CharSequence value) {
        return !isEmpty(value);
    }

    public static String of(String value, String defaultValue) {
        return isEmpty(value) ? defaultValue : value;
    }

    public static int of(String value, int defaultValue) {
        return isEmpty(value) ? defaultValue : Integer.parseInt(value);
    }

    public static String unescapeHTML4ForP(String html) {
        return StringEscapeUtils.unescapeHtml4(get(html))
                                .replaceAll("<p>", "")
                                .replaceAll("</p>", "<br />");
    }

    public static boolean indexOf(String findStr, String[] matchStr) {
        for (String match : matchStr) {
            if (findStr.contains(match)) {
                return true;
            }
        }

        return false;
    }
}
