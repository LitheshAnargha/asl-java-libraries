package com.asl.common.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import com.asl.common.message.CommonMessageService;
//import com.asl.common.properties.AslCommonProperties;
import com.asl.common.time.CommonTimeService;
import com.asl.common.util.CommonUtilService;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common service class which can be extended by other classes in this package
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
public abstract class CommonService implements ApplicationContextAware{
	
	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final String COLON = ":";
	public static final String PIPE = "|";
	public static final String COMMA = ",";
	public static final String SEMI_COLON = ";";
	public static final String HYPHEN = "-";
	public static final String YES = "yes";
	public static final String TRUE = "true";
	public static final String NO = "no";
	public static final String FALSE = "false";
	public static final String LINE_FEED = "\n";
	public static final Locale ENGLISH_LOCALE = Locale.US;
	public static final String MESSAGES_FILE = "messages";
	public static final long   THOUSAND = 1000L;
	public static final String ERROR = "ERROR -> ";
	private static ApplicationContext APPLICATION_CONTEXT = null;
	
	//@Autowired
	//private AslCommonProperties aslCommonProperties;
	
	@Autowired
	private CommonUtilService commonUtilService;
	
	@Autowired
	private CommonMessageService commonMessageService;
	
	@Autowired
	private CommonTimeService commonTimeService;
	
	public void setApplicationContext(ApplicationContext aContext) throws BeansException 
	{
		APPLICATION_CONTEXT = aContext;
	}//eof setApplicationContext

	public static ApplicationContext getApplicationContext() 
	{
	      return APPLICATION_CONTEXT;
	}//eof getApplicationContext
	
	public void logMessageFromFramework(String aMessage)
	{
		logMessage(aMessage);
	}//eof logMessageFromFramework
	
	/**
	 * 
	 * @param aLogger
	 * @param aMessage
	 */
	public void logMessage(Logger aLogger, String aMessage)
	{
		boolean isEnabledApplicable = isLoggerLoggingApplicable(aMessage);
		//System.out.println("isEnabledApplicable    -> "+isEnabledApplicable);
		if (isEnabledApplicable) 
		{
			//aLogger.debug(aMessage);
			aLogger.info(aMessage);
			//aLogger.warn(aMessage);
			//aLogger.trace(aMessage);
		}//eof if
	}//eof logMessage
	
	/**
	 * 
	 * @param aMessage
	 * @return
	 */
	private boolean isLoggerLoggingApplicable(String aMessage)
	{
		
		try
		{
			if (APPLICATION_CONTEXT == null) 
			{
				System.out.println("Application context is null or empty! Just enabling logging for startup!");
				return true;
			}//eof if
			
			/**if (aslCommonProperties == null) 
			{
				aslCommonProperties = APPLICATION_CONTEXT.getBean(aslCommonProperties.getClass());
			}//eof if**/
			
			if (APPLICATION_CONTEXT instanceof ConfigurableApplicationContext) 
			{
			    ConfigurableApplicationContext loConfigurableApplicationContext = (ConfigurableApplicationContext) 
			    APPLICATION_CONTEXT;
			    if (loConfigurableApplicationContext.isActive()==false) return true;
			}//eof if
			
			Environment loEnvironment = APPLICATION_CONTEXT.getBean(Environment.class);
			
			String lsLoggingEnabled = loEnvironment.getProperty("asl.common.logging.enabled");
			String lsSopLoggingEnabled = loEnvironment.getProperty("asl.common.logging.sop.enabled");
			
			//System.out.println("lsLoggingEnabled    -> "+lsLoggingEnabled);
			//System.out.println("lsSopLoggingEnabled -> "+lsSopLoggingEnabled);
			
			/**boolean isLoggingEnabled = aslCommonProperties.loggingEnabled;
			System.out.println("loggingEnabled -> "+isLoggingEnabled);
			boolean isLoggingSopEnabled = aslCommonProperties.loggingSopEnabled;
			System.out.println("isLoggingSopEnabled -> "+isLoggingSopEnabled);**/
			
			boolean isLogEnabled = isEnabled(lsLoggingEnabled);
			//System.out.println("isLogEnabled    -> "+isLogEnabled);
			
			if (isLogEnabled == false) return false;
			
			boolean isSopLogEnabled = isEnabled(lsSopLoggingEnabled);
			//System.out.println("isSopLogEnabled    -> "+isSopLogEnabled);
			
			if (isSopLogEnabled == true) 
			{
				System.out.println(aMessage);
				return false;
			}//eof if
		}
		catch(Exception aException)
		{
			//During shutdown ApplicationContext may not be accessible hence return true! 
			return true;
		}
		return true;
	}//eof isLoggerLoggingApplicabale
	
	/**
	 * 
	 * @param aText
	 * @return
	 */
	private boolean isEnabled(String aText)
	{
		if (aText == null) return false;
		aText = aText.trim();
		if (aText.length()==0) return false;
		aText = aText.toLowerCase();
		if (aText.equalsIgnoreCase(TRUE)) return true;
		if (aText.equalsIgnoreCase(YES)) return true;
		return false;
	}//eof isEnabled
	
	/**
	 * 
	 * @param aMessage
	 */
	protected void logMessage(String aMessage)
	{
		boolean isEnabled = isLoggerLoggingApplicable(aMessage);
		if (isEnabled) logMessageDetails(aMessage);
	}//eof logMessage
	
	/**
	 * 
	 * @param aLogger
	 * @param aMessage
	 */
	protected void logError(Logger aLogger, String aMessage)
	{
		if (aLogger == null) return;
		String lsMessage = ERROR + aMessage;
		aLogger.error(lsMessage);
	}//eof logError
	
	/**
	 * 
	 * @param aMessage
	 */
	protected abstract void logMessageDetails(String aMessage);
	
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
}//eof class
