package com.wso2.loggingremote.util;

public class LoggerNotFoundException extends Exception {
    public LoggerNotFoundException() {
        super("Logger name not found. Please check the logger name and try again");
    }

    public LoggerNotFoundException(String error) {
        super(error);
    }
}
