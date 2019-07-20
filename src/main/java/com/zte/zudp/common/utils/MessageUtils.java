package com.zte.zudp.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-02-09.
 */
public class MessageUtils {

    private static MessageSource messageSource;

    /**
     * 根据 消息键 和 参数 获取消息
     *
     * @param code 消息键
     * @param args 参数
     * @return 对应的消息
     */
    public static String message(String code, Object... args) {

        if (messageSource == null) {
            messageSource = SpringUtils.getBean(MessageSource.class);
        }

        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
