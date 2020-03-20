package com.wso2.loggingremote.controller;

import com.wso2.loggingremote.modal.ServerConfig;
import com.wso2.loggingremote.service.LoggingService;
import com.wso2.loggingremote.util.CommonUtil;
import com.wso2.loggingremote.util.Constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerMain {
    public static void main(String[] args) {
        ServerConfig serverConfig = CommonUtil.loadDefaultServerConfig();
        CommonUtil.initialize(serverConfig.getSystemProperties());
        LoggingService loggingService = new LoggingService();
        HashMap<String, String> params = new HashMap<String, String>();
        try {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase(Constants.HELP_TEXT)) {
                    CommonUtil.printHelp();

                } else {
                    String paramList[] = {Constants.CONFIG_TEXT, Constants.STARTS_WITH_TEXT, Constants.KEYSTORE_PASSWORD, Constants.USER_PASSWORD, Constants.SEARCH_TEXT, Constants.UPDATE_TEXT};
                    List tempList = Arrays.asList(args);
                    try {
                        for (String stringValue : paramList) {
                            if (tempList.contains(stringValue)) {
                                String value = null;
                                if (stringValue.equals(Constants.STARTS_WITH_TEXT)) {
                                    value = "TRUE";
                                } else {
                                    value = args[tempList.indexOf(stringValue) + 1];
                                }
                                params.put(stringValue, value);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        if (tempList.contains(Constants.UPDATE_TEXT)) {
                            CommonUtil.printUpdateErrors();
                        } else {
                            CommonUtil.printSearchErrors();
                        }
                    } catch (Exception e) {
                        CommonUtil.printCommonErrors();
                    }

                    if (params.get(Constants.CONFIG_TEXT) != null) {
                        serverConfig = CommonUtil.loadServerConfig(params.get(Constants.CONFIG_TEXT));
                        CommonUtil.initialize(serverConfig.getSystemProperties());
                        serverConfig = CommonUtil.updatePasswords(serverConfig, args);
                    } else {
                        serverConfig = CommonUtil.initializeWithEnv(serverConfig);
                        serverConfig = CommonUtil.updatePasswords(serverConfig, args);
                    }

                    if (params.get(Constants.SEARCH_TEXT) != null) {
                        loggingService.listLogs(serverConfig, params.get(Constants.SEARCH_TEXT), params.get(Constants.STARTS_WITH_TEXT) != null);
                    } else if (params.get(Constants.UPDATE_TEXT) != null) {
                        loggingService.updateLogs(serverConfig, CommonUtil.loadBulkLoggerConfig(params.get(Constants.UPDATE_TEXT)));
                    } else {
                        CommonUtil.printCommonErrors();
                    }
                }
            } else {
                CommonUtil.printCommonErrors();
            }
        } catch (Exception exception) {
            System.out.print(Constants.ERROR_PRE_FIX);
            System.out.println(exception.getMessage() + " while executing the application " + exception.getStackTrace());
        }
    }
}
