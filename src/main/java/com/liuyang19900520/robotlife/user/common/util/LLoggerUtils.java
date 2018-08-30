package com.liuyang19900520.robotlife.user.common.util;

import org.slf4j.LoggerFactory;

/**
 * @program: sales-promotion-api
 * @description:
 * @author: LiuYang
 * @create: 2018-06-27 10:32
 **/
public class LLoggerUtils {

    public static <T> org.slf4j.Logger Logger(Class<T> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 打印到指定的文件下
     *
     * @param desc 日志文件名称
     * @return
     */
    public static org.slf4j.Logger Logger(LogFileName desc) {
        return LoggerFactory.getLogger(desc.getLogFileName());
    }


    public enum LogFileName {

        //配置到logback.xml中的logger name="vipUser"
        Business("business");

        private String logFileName;

        LogFileName(String fileName) {
            this.logFileName = fileName;
        }

        public String getLogFileName() {
            return logFileName;
        }

        public void setLogFileName(String logFileName) {
            this.logFileName = logFileName;
        }

        public static LogFileName getAwardTypeEnum(String value) {
            LogFileName[] arr = values();
            for (LogFileName item : arr) {
                if (null != item && LStringUtils.isNotBlank(item.logFileName)) {
                    return item;
                }
            }
            return null;
        }
    }


}
