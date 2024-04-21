package com.asl.common.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.asl.common.service.CommonService;


@Aspect
@Component
public class AslMethodLoggerImpl {
	
	private static final String COLON = " : ";
	private static final String FORWARD_ARROW = " --->> ";
	private static final String BACKWARD_ARROW = " <<--- ";
	private static final String METHOD_SIGN = " ()";
	private static  String HYPHEN = "'";
	private static final String LINE_FEED = "\n";
	
	@Around("@annotation(com.asl.common.annotation.AslMethodLogger)")
	public Object showMethodLevelLogging(ProceedingJoinPoint aJoinPoint) throws Throwable
	{
		Object loProceed = null;
		Object loObject = null;
		String lsMethodName = null;
		try
		{
			loObject = aJoinPoint.getTarget();
			String lsClassShortName = loObject.getClass().getSimpleName();
			Signature loSignature = aJoinPoint.getSignature();
			lsMethodName = lsClassShortName + COLON + loSignature.getName();
			logStartMessage(loObject,lsMethodName);
			loProceed = aJoinPoint.proceed();
		}
		catch(Exception aException)
		{
			aException.printStackTrace();
			throw aException;
		}
		finally
		{
			logEndMessage(loObject,lsMethodName);
		}
		return loProceed;
	}//eof showMethodLevelLogging
	
	private void logStartMessage(Object aObject, String aMethodName)
	{
		String lsLoggerText = FORWARD_ARROW + aMethodName + METHOD_SIGN;
		if (CommonService.class.isAssignableFrom(aObject.getClass()))
		{
			String lsHeader = getHeader(HYPHEN, lsLoggerText.length()-1);
			StringBuffer loBuffer = new StringBuffer();
			loBuffer.append(LINE_FEED);
			//loBuffer.append(lsHeader);
			loBuffer.append(lsLoggerText);
			loBuffer.append(lsHeader);
			CommonService loCommonService =  (CommonService) aObject;
			loCommonService.logMessageFromFramework(loBuffer.toString());
		}//eof if
	}//eof logStartMessage
	
	private void logEndMessage(Object aObject, String aMethodName)
	{
		String lsLoggerText = BACKWARD_ARROW + aMethodName + METHOD_SIGN;
		if (CommonService.class.isAssignableFrom(aObject.getClass()))
		{
			String lsHeader = getHeader(HYPHEN, lsLoggerText.length()-1);
			StringBuffer loBuffer = new StringBuffer();
			loBuffer.append(LINE_FEED);
			//loBuffer.append(lsHeader);
			loBuffer.append(lsLoggerText);
			loBuffer.append(lsHeader);
			CommonService loCommonService =  (CommonService) aObject;
			loCommonService.logMessageFromFramework(loBuffer.toString());
		}//eof if
	}//eof logStartMessage
	
	private String getHeader(String aChar, int aLen) 
	{
		StringBuffer loBuffer = new StringBuffer();
		loBuffer.append(LINE_FEED);
		int piCount = 0;
		while (piCount <= aLen)
		{
			loBuffer.append(aChar);
			piCount = piCount + 1;
		}//eof while
		loBuffer.append(LINE_FEED);
		return loBuffer.toString();
	}//eof getHeader

}
