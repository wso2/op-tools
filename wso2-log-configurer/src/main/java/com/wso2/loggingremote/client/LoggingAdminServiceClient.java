package com.wso2.loggingremote.client;

import com.wso2.loggingremote.util.CommonUtil;
import com.wso2.loggingremote.util.Constants;
import com.wso2.loggingremote.util.LoggerNotFoundException;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.wso2.carbon.logging.service.LoggingAdminException;
import org.wso2.carbon.logging.service.LoggingAdminStub;

import java.rmi.RemoteException;

public class LoggingAdminServiceClient {

    private final String serviceName = "LoggingAdmin";
    private LoggingAdminStub loggingAdminStub;

    /**
     * @param backEndUrl
     * @param sessionCookie
     * @throws AxisFault
     */
    public LoggingAdminServiceClient(String backEndUrl, String sessionCookie) throws AxisFault {
        loggingAdminStub = new LoggingAdminStub(backEndUrl + "/services/" + serviceName);
        ServiceClient serviceClient_;
        Options option_;

        serviceClient_ = loggingAdminStub._getServiceClient();
        option_ = serviceClient_.getOptions();
        option_.setManageSession(true);
        option_.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING, sessionCookie);
    }

    public LoggingAdminStub.GetAllLoggerDataResponse searchByLogName(String logName, boolean startWith) {
        LoggingAdminStub.GetAllLoggerDataResponse resp = null;

        try {
            LoggingAdminStub.GetAllLoggerData loggerData = new LoggingAdminStub.GetAllLoggerData();
            loggerData.setLogNameFilter(logName);
            loggerData.setBeginsWith(startWith);

            resp = this.loggingAdminStub.getAllLoggerData(loggerData);

        } catch (RemoteException e) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println(e.getStackTrace());
        }

        return resp;
    }

    public LoggingAdminStub.GetLoggerDataResponse getCurrentLoggerDetails(String loggerName) {
        LoggingAdminStub.GetLoggerDataResponse resp = new LoggingAdminStub.GetLoggerDataResponse();
        try {
            LoggingAdminStub.GetLoggerData loggerData = new LoggingAdminStub.GetLoggerData();
            loggerData.setLoggerName(loggerName);
            resp = this.loggingAdminStub.getLoggerData(loggerData);
        } catch (RemoteException e) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.print(e.getMessage());
        }
        return resp;
    }

    public boolean updateLogLevel(String logName, String level, Boolean additivity) {
        LoggingAdminStub.UpdateLoggerData loggerData;
        boolean result = false;
        try {
            loggerData = new LoggingAdminStub.UpdateLoggerData();
            loggerData.setLoggerName(logName);
            loggerData.setLoggerLevel(CommonUtil.resolveLog(level));
            loggerData.setAdditivity(additivity);
            loggerData.setPersist(true);
            this.loggingAdminStub.updateLoggerData(loggerData);
            result = true;
        } catch (RemoteException e) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println(e.getStackTrace());
        } catch (LoggingAdminException e) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println(e.getStackTrace());
        } catch (LoggerNotFoundException e) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println(e.getStackTrace());
        }
        return result;
    }

}
