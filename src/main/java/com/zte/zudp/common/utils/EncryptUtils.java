package com.zte.zudp.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-12.
 */
public class EncryptUtils {

    public static byte[] toMD5(StringBuilder str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = str.toString().getBytes();
            md5.update(bytes);
            return md5.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("can't to md5!");
    }
}
