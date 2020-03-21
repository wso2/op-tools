package com.wso2.loggingremote.modal;

public class Configurations {
    private ServerConfig serverConfig;
    private UpdateLoggerConfig loggerConfig;

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public UpdateLoggerConfig getLoggerConfig() {
        return loggerConfig;
    }

    public void setLoggerConfig(UpdateLoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }
}
