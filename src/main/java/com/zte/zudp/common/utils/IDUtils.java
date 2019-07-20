package com.zte.zudp.common.utils;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 各种唯一性ID的算法的工具类
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-10.
 */
public class IDUtils {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装了 JDK 自带的UUID
     *
     * @return JDK自带的UUID
     */
    public static String defaultUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }
}
