package com.chartify.chartify.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class LogUtil {

    /**
     * 打印debug级别普通格式日志  ,如果带参数用{0},{1},..替换
     * 如：
     * LogUtil.error(logger,e,"hello,{0},这里很sorry的发生了{1}异常",亲,数据库操作);
     *
     * @param logger
     * @param msg
     */
    @SuppressWarnings("unused")
    public static void debug(Logger logger, String msg, Object... params) {
        if (logger.isDebugEnabled()) {

            if (ArrayUtils.isEmpty(params)) {
                logger.debug(LogUtil.buildLocationInfo() + msg);
            } else {
                logger.debug(LogUtil.buildLocationInfo() + StringUtil.format(msg, params));
            }
        }
    }


    /**
     * 打印info级别普通格式日志,如果带参数用{0},{1},..替换
     * 如：
     * LogUtil.error(logger,e,"hello,{0},这里很sorry的发生了{1}异常",亲,数据库操作);
     *
     * @param logger
     * @param msg
     * @param params
     */
    public static void info(Logger logger, String msg, Object... params) {
        if (logger.isInfoEnabled()) {
            if (ArrayUtils.isEmpty(params)) {
                logger.info(LogUtil.buildLocationInfo() + msg);
            } else {
                logger.info(LogUtil.buildLocationInfo() + StringUtil.format(msg, params));
            }
        }
    }

    /**
     * 打印warn级别普通格式日志
     *
     * @param logger
     * @param msg
     */
    public static void warn(Logger logger, String msg, Object... params) {
        if (logger.isWarnEnabled()) {
            if (ArrayUtils.isEmpty(params)) {
                logger.warn(LogUtil.buildLocationInfo() + msg);
            } else {
                logger.warn(LogUtil.buildLocationInfo() + StringUtil.format(msg, params));
            }
        }
    }

    /**
     * 打印error级别带异常的格式化日志,如果带参数用{0},{1},..替换
     * 如：
     * LogUtil.error(logger,e,"hello,{0},这里很sorry的发生了{1}异常",亲,数据库操作);
     *
     * @param logger
     * @param msg
     * @param params
     */
    public static void error(Logger logger, String msg, Object... params) {
        if (logger.isErrorEnabled()) {
            if (ArrayUtils.isEmpty(params)) {
                logger.error(LogUtil.buildLocationInfo() + msg);
            } else {
                logger.error(LogUtil.buildLocationInfo() + StringUtil.format(msg, params));
            }
        }

    }


    /**
     * 打印warn级别普通格式日志,如果带参数用{0},{1},..替换
     * 如：
     * LogUtil.error(logger,e,"hello,{0},这里很sorry的发生了{1}异常",亲,数据库操作);*
     * @param logger
     * @param msg
     */
    public static void warn(Logger logger, Throwable e, String msg, Object... params) {
        if (logger.isWarnEnabled()) {
            if (ArrayUtils.isEmpty(params)) {
                logger.warn(LogUtil.buildLocationInfo() + msg, e);
            } else {
                logger.warn(LogUtil.buildLocationInfo() + StringUtil.format(msg, params), e);
            }
        }
    }


    /**
     * 打印error级别带异常的格式化日志,如果带参数用{0},{1},..替换
     * 如：
     * LogUtil.error(logger,e,"hello,{0},这里很sorry的发生了{1}异常",亲,数据库操作);*
     * @param logger
     * @param e
     * @param msg
     * @param params
     */
    public static void error(Logger logger, Throwable e, String msg, Object... params) {
        if (logger.isErrorEnabled()) {
            if (ArrayUtils.isEmpty(params)) {
                logger.error(LogUtil.buildLocationInfo() + msg, e);
            } else {
                logger.error(LogUtil.buildLocationInfo() + StringUtil.format(msg, params), e);
            }
        }

    }


    /**
     * 获取调用 LogUtil 的类名,方法及行号
     *
     * @return
     */
    private static String buildLocationInfo() {
        StringBuilder header = new StringBuilder();
        // LOG4J2-1029 new Throwable().getStackTrace is faster than Thread.currentThread().getStackTrace().
        final StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();

        for (int i = 0; i < stackTraceElements.length - 1; i++) {
            StackTraceElement currentStackTrace = stackTraceElements[i];
            StackTraceElement nextStackTrace = stackTraceElements[i + 1];

            /**
             * 当前堆栈节点在 LogUtil
             * 而下一堆栈节点不在LogUtils
             * 则说明下一节点即为调用 LogUtil 的调用方
             */
            if (LogUtil.class.getName().equals(currentStackTrace.getClassName()) &&
                    !LogUtil.class.getName().equals(nextStackTrace.getClassName())) {
                String stackTrace = nextStackTrace.toString();
                header.append(" ").append(StringUtils.removeStart(stackTrace, nextStackTrace.getClassName() + "."));
                break;
            }
        }
        return header.append(":").toString();
    }

}