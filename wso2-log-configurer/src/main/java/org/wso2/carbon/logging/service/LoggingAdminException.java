
/**
 * LoggingAdminException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v23  Built on : Jan 30, 2018 (01:18:22 UTC)
 */

package org.wso2.carbon.logging.service;

public class LoggingAdminException extends java.lang.Exception{

    private static final long serialVersionUID = 1564118087050L;
    
    private org.wso2.carbon.logging.service.LoggingAdminStub.LoggingAdminExceptionE faultMessage;

    
        public LoggingAdminException() {
            super("LoggingAdminException");
        }

        public LoggingAdminException(java.lang.String s) {
           super(s);
        }

        public LoggingAdminException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public LoggingAdminException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.wso2.carbon.logging.service.LoggingAdminStub.LoggingAdminExceptionE msg){
       faultMessage = msg;
    }
    
    public org.wso2.carbon.logging.service.LoggingAdminStub.LoggingAdminExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    