package com.chartify.chartify.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.text.MessageFormat;

public class StringUtil extends StringUtils {
    /**
     * 匹配占位符号的正则表达式，如{0}
     */
    private static final String TEMPLATE_REGEX = "\\{\\d}";

    public static String format(String msg, Object... params){
        if (StringUtils.isEmpty(msg)){
            return StringUtils.EMPTY;
        }
        if (params != null && params.length > 0){
            msg = MessageFormat.format(msg,params);
        }
        return msg.replaceAll(StringUtil.TEMPLATE_REGEX, StringUtils.EMPTY);
    }

    /**
     * 重写toString
     * @return
     */
    public static String toString(Object object){
        return ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
