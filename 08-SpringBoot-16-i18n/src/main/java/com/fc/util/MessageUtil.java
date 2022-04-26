package com.fc.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

// 目前没有使用模板引擎，所以稍微有点麻烦
@Component
public class MessageUtil {
    // 声明一个消息资源对象
    private static MessageSource messageSource;
    // 初始化
    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    // 获取指定的国际化翻译值
    public static String get(String msgKey) {
        try {
            return messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            return msgKey;
        }
    }
}
