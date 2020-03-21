package com.wso2.loggingremote.service;

import com.wso2.loggingremote.client.LoggingAdminServiceClient;
import com.wso2.loggingremote.client.LoginAdminServiceClient;
import com.wso2.loggingremote.modal.ServerConfig;
import com.wso2.loggingremote.modal.UpdateLoggerConfig;
import com.wso2.loggingremote.util.Constants;
import org.apache.axis2.AxisFault;
import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.authenticator.stub.LoginAuthenticationExceptionException;
import org.wso2.carbon.authenticator.stub.LogoutAuthenticationExceptionException;
import org.wso2.carbon.logging.service.LoggingAdminStub;

import java.rmi.RemoteException;

public class LoggingService {

    public void listLogs(ServerConfig configurations, String logName, boolean startsWith) {
        LoginAdminServiceClient authclient;
        String session = "";

        String hostnames[] = configurations.getHostname().split(",");
        for (String hostname : hostnames) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println(" Host : " + hostname);
            System.out.println();


            try {
                authclient = new LoginAdminServiceClient(hostname);
                session = authclient.authenticate(configurations.getUsername(),
                        configurations.getPassword(), hostname);
                if (session == null) {
                    System.out.print(Constants.ERROR_PRE_FIX);
                    System.out.println(Constants.LOGGING_IN_FAILED_TEXT);
                    return;
                }
            } catch (RemoteException e) {
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.LOGGING_IN_FAILED_TEXT + " : " + e.getMessage());
                return;
            } catch (LoginAuthenticationExceptionException e) {
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.LOGGING_IN_FAILED_TEXT + " : " + e.getMessage());
                return;
            }

            try {
                LoggingAdminServiceClient loggingAdminServiceClient = new LoggingAdminServiceClient(hostname, session);
                LoggingAdminStub.GetAllLoggerDataResponse logDataList =
                        loggingAdminServiceClient.searchByLogName(logName, startsWith);

                if (logDataList.get_return() != null && logDataList.get_return().length > 0) {
                    System.out.println(String.format("||%-80s|%-10s|%-30s|%-10s||", "Name", "Level", "Parent", "Additivity"));

                    for (LoggingAdminStub.LoggerData logdata : logDataList.get_return()) {
                        System.out.println(String.format("||%-80s|%-10s|%-30s|%-10s||", logdata.getName(), logdata.getLevel(), logdata.getParentName(), logdata.getAdditivity()));
                        //System.out.println(logdata.getName() + "\t|" + logdata.getLevel() + "\t|" + logdata.getParentName() + "\t|" + logdata.getAdditivity());
                    }
                } else {
                    System.out.println("No matching loggers found");
                }
                authclient.logOut();
            } catch (AxisFault axisFault) {
                System.out.println("Searching failed");
                return;
            } catch (RemoteException e) {
                System.out.println(Constants.LOGGING_OUT_FAILED_TEXT + " : " + e.getMessage());
                return;
            } catch (LogoutAuthenticationExceptionException e) {
                System.out.println(Constants.LOGGING_OUT_FAILED_TEXT + " : " + e.getMessage());
                return;
            }

            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public void updateLogs(ServerConfig serverConfig, UpdateLoggerConfig[] loggerBulkConfig) {
        LoginAdminServiceClient authclient;
        System.out.println("Logging service started for update log levels");
        String hostnames[] = serverConfig.getHostname().split(",");
        for (String hostname : hostnames) {
            System.out.println("Updating the host : " + hostname);

            String session = "";
            try {
                authclient = new LoginAdminServiceClient(hostname);
                session = authclient.authenticate(serverConfig.getUsername(),
                        serverConfig.getPassword(), hostname);
                if (session == null) {
                    System.out.println(Constants.LOGGING_IN_FAILED_TEXT);
                    return;
                }
                System.out.println("User authenticated successfully for " + hostname+ " !");
            } catch (RemoteException e) {
                e.printStackTrace();
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.LOGGING_IN_FAILED_TEXT + " : " + e.getMessage());
                return;
            } catch (LoginAuthenticationExceptionException e) {
                e.printStackTrace();
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.LOGGING_IN_FAILED_TEXT + " : " + e.getMessage());
                return;
            }


            try {
                System.out.println("Reading logger changes from file...");
                LoggingAdminServiceClient loggingAdminServiceClient =
                        new LoggingAdminServiceClient(hostname, session);

                for (UpdateLoggerConfig loggerConfig : loggerBulkConfig) {
                    if (!updateLogsAbs(loggerConfig, loggingAdminServiceClient)) {
                        System.out.println(Constants.UPDATE_FAILED_TEXT);
                    }
                }

                System.out.println("Logs updated successfully for " + hostname +" !");
                authclient.logOut();
                System.out.println("User logged out successfully from " + hostname);
            } catch (AxisFault axisFault) {
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.UPDATE_FAILED_TEXT + " : " + axisFault.getMessage());
                return;
            } catch (RemoteException e) {
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.LOGGING_OUT_FAILED_TEXT + " : " + e.getMessage());
                return;
            } catch (LogoutAuthenticationExceptionException e) {
                System.out.print(Constants.ERROR_PRE_FIX);
                System.out.println(Constants.LOGGING_OUT_FAILED_TEXT + " : " + e.getMessage());
                return;
            }
            System.out.println("\t\t\t...");
            System.out.println("\n\n");

        }

    }

    private boolean updateLogsAbs(UpdateLoggerConfig loggerConfig, LoggingAdminServiceClient loggingAdminServiceClient) {
        boolean result = false;
        if (StringUtils.isEmpty(loggerConfig.getLoggerName())) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println("Specify logger name");
            return false;
        }
        if (StringUtils.isEmpty(loggerConfig.getLoggerLevel()) && loggerConfig.isAdditivity() == null) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println("Specify log level or additivity to update in logger " + loggerConfig.getLoggerName());
            return false;
        }
        if (StringUtils.isEmpty(loggerConfig.getLoggerLevel())) {
            System.out.println("Logger level not specified. Fetching current logger level..");
            loggerConfig.setLoggerLevel(loggingAdminServiceClient.getCurrentLoggerDetails(loggerConfig.getLoggerName()).get_return().getLevel());
            System.out.println("Fetching logger level successful!");
        }
        if (loggerConfig.isAdditivity() == null) {
            System.out.println("Additivity not specified. Fetching current additivity..");
            loggerConfig.setAdditivity(loggingAdminServiceClient.getCurrentLoggerDetails(loggerConfig.getLoggerName()).get_return().getAdditivity());
            System.out.println("Fetching additivity successful!");
        }

        result = loggingAdminServiceClient.updateLogLevel(loggerConfig.getLoggerName(), loggerConfig.getLoggerLevel(), loggerConfig.isAdditivity());

        System.out.println("Log updated successfully for " + loggerConfig.getLoggerName());

        return result;
    }

}
