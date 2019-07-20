package com.zte.zudp.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-06.
 */
public class IOUtils {

    public static void closeQuietly(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
