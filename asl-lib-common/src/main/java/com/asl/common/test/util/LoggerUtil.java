package com.asl.common.test.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asl.common.properties.AslCommonProperties;

@Service
public class LoggerUtil {
	
	public static final String ERROR = "ERROR -> ";
	

    public LoggerUtil(@Autowired @Qualifier("aslCommonProperties") AslCommonProperties aslCommonProperties) {
    	this.aslCommonProperties = aslCommonProperties;
    }
    
	private  AslCommonProperties aslCommonProperties;
	
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
	private  void logMessageDetails(String aMessage) {
		System.out.println(aMessage);
	}

}
