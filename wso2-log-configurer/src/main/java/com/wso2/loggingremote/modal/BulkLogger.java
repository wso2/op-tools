package com.wso2.loggingremote.modal;

import java.util.List;

public class BulkLogger {
    private List<UpdateLoggerConfig> bulkLogger;

    public List<UpdateLoggerConfig> getBulkLogger() {
        return bulkLogger;
    }

    public void setBulkLogger(List<UpdateLoggerConfig> bulkLogger) {
        this.bulkLogger = bulkLogger;
    }
}
