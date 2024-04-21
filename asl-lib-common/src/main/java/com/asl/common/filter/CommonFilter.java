package com.asl.common.filter;

import org.springframework.beans.factory.annotation.Autowired;

import com.asl.common.message.CommonMessageService;
import com.asl.common.properties.AslCommonProperties;
import com.asl.common.time.CommonTimeService;
import com.asl.common.util.CommonUtilService;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common Filter class which can be extended by other classes in this package
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
public abstract class CommonFilter {
	
	@Autowired
	private AslCommonProperties aslCommonConfiguration;
	
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
	protected void logMessage(String aMessage)
	{
		boolean isLoggingEnabled = aslCommonConfiguration.loggingEnabled;
		boolean isLoggingSopEnabled = aslCommonConfiguration.loggingSopEnabled;
		if (isLoggingEnabled == false) return;
		if (isLoggingSopEnabled == true) {
			System.out.println(aMessage);
			return;
		}//eof if
		logMessageDetails(aMessage);
	}//eof logMessage
	
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

}
