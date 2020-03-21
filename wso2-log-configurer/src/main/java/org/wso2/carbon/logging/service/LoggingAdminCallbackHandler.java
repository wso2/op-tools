
/**
 * LoggingAdminCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1-wso2v23  Built on : Jan 30, 2018 (01:18:22 UTC)
 */

    package org.wso2.carbon.logging.service;

    /**
     *  LoggingAdminCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class LoggingAdminCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public LoggingAdminCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public LoggingAdminCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for removeSyslogPattern method
            * override this method for handling normal response from removeSyslogPattern operation
            */
           public void receiveResultremoveSyslogPattern(
                    org.wso2.carbon.logging.service.LoggingAdminStub.RemoveSyslogPatternResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeSyslogPattern operation
           */
            public void receiveErrorremoveSyslogPattern(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for isStratosService method
            * override this method for handling normal response from isStratosService operation
            */
           public void receiveResultisStratosService(
                    org.wso2.carbon.logging.service.LoggingAdminStub.IsStratosServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isStratosService operation
           */
            public void receiveErrorisStratosService(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLoggerData method
            * override this method for handling normal response from getLoggerData operation
            */
           public void receiveResultgetLoggerData(
                    org.wso2.carbon.logging.service.LoggingAdminStub.GetLoggerDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLoggerData operation
           */
            public void receiveErrorgetLoggerData(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getSystemLog method
            * override this method for handling normal response from getSystemLog operation
            */
           public void receiveResultgetSystemLog(
                    org.wso2.carbon.logging.service.LoggingAdminStub.GetSystemLogResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSystemLog operation
           */
            public void receiveErrorgetSystemLog(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAppenderData method
            * override this method for handling normal response from getAppenderData operation
            */
           public void receiveResultgetAppenderData(
                    org.wso2.carbon.logging.service.LoggingAdminStub.GetAppenderDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAppenderData operation
           */
            public void receiveErrorgetAppenderData(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getSyslogData method
            * override this method for handling normal response from getSyslogData operation
            */
           public void receiveResultgetSyslogData(
                    org.wso2.carbon.logging.service.LoggingAdminStub.GetSyslogDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSyslogData operation
           */
            public void receiveErrorgetSyslogData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllLoggerData method
            * override this method for handling normal response from getAllLoggerData operation
            */
           public void receiveResultgetAllLoggerData(
                    org.wso2.carbon.logging.service.LoggingAdminStub.GetAllLoggerDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllLoggerData operation
           */
            public void receiveErrorgetAllLoggerData(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                


    }
    