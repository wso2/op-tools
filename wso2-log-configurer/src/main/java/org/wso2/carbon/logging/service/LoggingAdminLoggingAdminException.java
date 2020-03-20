
/**
 * LoggingAdminLoggingAdminException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v23  Built on : Jan 30, 2018 (01:18:22 UTC)
 */

package org.wso2.carbon.logging.service;

public class LoggingAdminLoggingAdminException extends java.lang.Exception{

    private static final long serialVersionUID = 1564118087043L;
    
    private org.wso2.carbon.logging.service.LoggingAdminStub.LoggingAdminLoggingAdminException faultMessage;

    
        public LoggingAdminLoggingAdminException() {
            super("LoggingAdminLoggingAdminException");
        }

        public LoggingAdminLoggingAdminException(java.lang.String s) {
           super(s);
        }

        public LoggingAdminLoggingAdminException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public LoggingAdminLoggingAdminException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.logging.service.LoggingAdminStub.LoggingAdminLoggingAdminException msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.logging.service.LoggingAdminStub.LoggingAdminLoggingAdminException getFaultMessage(){
       return faultMessage;
    }
}
    