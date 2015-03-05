/***********************************************
 * Filename       : MessageUtil.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 03/05/2015
 ************************************************/

package com.innovaee.eorder.support;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @Title: MessageUtil
 * @Description: 系统消息资源工具助手
 * 
 * @version V1.0
 */
public class MessageUtil {

    private static Logger logger = Logger.getLogger(MessageUtil.class);
    
    /**
     * 获取指定的消息资源
     * @param key 消息资源键值
     * @return 消息资源字符串值
     */
    public static String getMessage(final String key, final String... values) {
        try {
            ApplicationContext context = ApplicationContextProvider.getContext();
            
            MessageSource messageSource = (MessageSource) context.getBean("messageSource");
            
            return messageSource.getMessage(key, values, Locale.CHINA);
        } catch (Exception exception){
            logger.error("[getMessageException=" + exception.getMessage() + "]");
            return key;
        }
    }
}
