package com.zte.zudp.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 编码工具类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public class EncodeUtils {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 针对浏览器编码的解码操作
     * @param src 浏览器传入的数据值，传入值不能为 null！！！
     * @return 通过 ISO-8859-1 解码，再编码为 UTF-8
     */
    public static String decodeBrower(String src) {
        return new String(src.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    /**
     * 将字节数组转为十六进制字符串
     *
     * @param data 转码的字节数组
     * @return 转换后的十六进制字符串
     */
    public static String encodeHex(byte[] data) {
        return new String(Hex.encodeHex(data));
    }

    /**
     * Hex解码字符数组
     *
     * @param data 解码前的字符数组
     * @return 解码后后的十六进制字符串
     * @throws DecoderException 当传入奇数个字符或非法的字符时会抛出此异常
     */
    public static byte[] decodeHex(char[] data) throws DecoderException {
        return Hex.decodeHex(data);
    }

    /**
     * Base64编码字符串
     *
     * @param data .
     * @return .
     */
    public static String encodeBase64(String data) {
        return encodeBase64(data, StandardCharsets.UTF_8);
    }

    /**
     * Base64编码字符串
     *
     * @param data .
     * @return .
     */
    public static String encodeBase64(String data, Charset charset) {
        return new String(encodeBase64(data.getBytes(charset)));
    }

    /**
     * Base64编码字节数组
     *
     * @param data .
     * @return .
     */
    public static byte[] encodeBase64(byte[] data) {
        return Base64.encodeBase64(data);
    }

    /**
     * Base64解码字符串
     *
     * @param data .
     * @return .
     */
    public static String decodeBase64(String data) {
        return decodeBase64(data, StandardCharsets.UTF_8);
    }

    /**
     * Base64解码字符串
     *
     * @param data .
     * @return .
     */
    public static String decodeBase64(String data, Charset charset) {
        return new String(decodeBase64(data.getBytes(charset)), charset);
    }

    /**
     * Base64解码字节数组
     *
     * @param data .
     * @return .
     */
    public static byte[] decodeBase64(byte[] data) {
        return Base64.decodeBase64(data);
    }

    /**
     * 对字符串进行 URL 编码，Encode默认为UTF-8
     *
     * @param part .
     * @return .
     */
    public static String encodeURL(String part) {
        try {
            return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
      /* ignore */
        }
        return "";
    }

    /**
     * 对字符串进行 URL 解码，Encode默认为UTF-8
     *
     * @param part .
     * @return .
     */
    public static String decodeURL(String part) {
        try {
            return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
      /* ignore */
        }
        return "";
    }

    /**
     * <p>通过 HTML 实体修改字符串中的特殊字符字符（支持所有已知的 HTML 4.0 实体）</p>
     *
     * 如 <code>"bread" & "butter"</code> 会变成 <code>&quot;bread&quot; &amp; &quot;butter&quot;</code>
     *
     * @param html .
     * @return .
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * 还原字符串中 HTML 实体中的特殊字符
     *
     * @param html .
     * @return .
     */
    public static String unescapeHtml(String html) {
        return StringEscapeUtils.unescapeHtml4(html);
    }

    /**
     * Xml 实体替换
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml11(xml);
    }

    /**
     * Xml 实体还原
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }
}
