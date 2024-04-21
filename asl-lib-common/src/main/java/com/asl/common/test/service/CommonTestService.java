package com.asl.common.test.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.asl.common.message.CommonMessageService;
import com.asl.common.properties.AslCommonProperties;
import com.asl.common.time.CommonTimeService;
import com.asl.common.util.CommonUtilService;

public abstract class CommonTestService {
	
	public static final String ERROR = "ERROR -> ";
	public static final String LOG_TYPE_DEBUG = "debug";
	public static final String LOG_TYPE_INFO = "info";
	public static final String LOG_TYPE_ERROR = "error";
	public static final String EMPTY = "";
	
	@Autowired
	@Qualifier("aslCommonProperties")
	private  AslCommonProperties aslCommonProperties;
	
	@Autowired
	private CommonUtilService commonUtilService;
	
	@Autowired
	private CommonMessageService commonMessageService;
	
	@Autowired
	private CommonTimeService commonTimeService;
	
	/**
	 * 
	 * @param aMessage
	 */
	public void logMessage(String aMessage)
	{
		boolean isLoggingEnabled = aslCommonProperties.loggingEnabled;
		boolean isLoggingSopEnabled = aslCommonProperties.loggingSopEnabled;
		if (isLoggingEnabled == false) return;
		if (isLoggingSopEnabled == true) {
			System.out.println(aMessage);
			return;
		}//eof if
		logMessageDetails(aMessage);
	}//eof logMessage
	
	/**
	 * 
	 * @param aLogger
	 * @param aMessage
	 */
	public void logError(Logger aLogger, String aMessage)
	{
		if (aLogger == null) return;
		String lsMessage = ERROR + aMessage;
		aLogger.error(lsMessage);
	}//eof logError
	
	/**
	 * 
	 * @param aMessage
	 */
	protected  void logMessageDetails(String aMessage) {
		System.out.println(aMessage);
	}
	
	/**
	 * 
	 * @return
	 */
	protected CommonUtilService getCommonUtilService() {
		return this.commonUtilService;
	}
	
	/**
	 * 
	 * @return
	 */
	protected CommonMessageService getcommonMessageService() {
		return this.commonMessageService;
	}
	
	/**
	 * 
	 * @return
	 */
	protected CommonTimeService getcommonTimeService() {
		return this.commonTimeService;
	}
	
	protected void logMessage(Logger aLogger, String aLogType, String aMessage)
	{
		if (aLogType == null) aLogType = EMPTY;
		if (aLogger == null) return;
		if (aLogType.equalsIgnoreCase(LOG_TYPE_DEBUG)) aLogger.debug(aMessage);
		if (aLogType.equalsIgnoreCase(LOG_TYPE_INFO)) aLogger.info(aMessage);
		if (aLogType.equalsIgnoreCase(LOG_TYPE_ERROR)) aLogger.error(aMessage);
	}//eof logMessage

}
