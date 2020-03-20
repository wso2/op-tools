package com.wso2.loggingremote.modal;

public class UpdateLoggerConfig {
    private String loggerName;
    private String loggerLevel;
    private Boolean additivity;

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerLevel() {
        return loggerLevel;
    }

    public void setLoggerLevel(String loggerLevel) {
        this.loggerLevel = loggerLevel;
    }

    public Boolean isAdditivity() {
        return additivity;
    }

    public void setAdditivity(Boolean additivity) {
        this.additivity = additivity;
    }
}
