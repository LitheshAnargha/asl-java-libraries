package com.asl.common.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.asl.common.service.CommonService;

@Aspect
@Component
public class AslMethodTimerImpl {
	
	private static final String COLON = ":";
	private static final String EMPTY = "";
	private static final String SPACE = " ";
	private static final String PIPE = "|";
	private static final String ZERO = "0";
	private static final String CLOSE = "]";
	private static final String HYPHEN_1 = ",";
	private static final String HYPHEN_2 = "'";
	private static final String LINE_FEED = "\n";
	private static final String METHOD_SIGN = " ()";
	private static final String TIME_TAKEN_FOR_TEXT1 = "Time taken for executing { ";
	private static final String TIME_TAKEN_FOR_TEXT2 = " }in MM:SS:ssss is [";
	
	@Around("@annotation(com.asl.common.annotation.AslMethodTimer)")
	public Object logExceutionTime(ProceedingJoinPoint aJoinPoint) throws Throwable
	{
		StopWatch loStopWatch = null;
		Object loProceed = null;
		Object loObject = aJoinPoint.getTarget();
		try
		{
			loStopWatch = new StopWatch();
			loStopWatch.start();
			loProceed = aJoinPoint.proceed();
		}
		catch(Exception aException)
		{
			aException.printStackTrace();
			throw aException;
		}
		finally
		{
			loStopWatch.stop();
			if (CommonService.class.isAssignableFrom(loObject.getClass()))
			{
				long lpTimeTaken = loStopWatch.getTotalTimeMillis();
				String lsTimeTaken = getTimeText(lpTimeTaken);
				String lsClassName = loObject.getClass().getSimpleName();
				Signature loSig = aJoinPoint.getSignature();
				String lsText = lsClassName + COLON + loSig.getName() + METHOD_SIGN;
				String lsTextToDisplay = TIME_TAKEN_FOR_TEXT1 + lsText + TIME_TAKEN_FOR_TEXT2;
				String lsFinalText = PIPE + SPACE + lsTextToDisplay + lsTimeTaken + CLOSE + SPACE + PIPE;
				String lsHeader1 = getHeader(HYPHEN_1, lsFinalText.length()-1);
				String lsHeader2 = getHeader(HYPHEN_2, lsFinalText.length()-1);
				StringBuffer loBuffer = new StringBuffer();
				loBuffer.append(LINE_FEED);
				loBuffer.append(lsHeader1);
				loBuffer.append(lsFinalText);
				loBuffer.append(lsHeader2);
				CommonService loCommonService = (CommonService) loObject;
				loCommonService.logMessageFromFramework(loBuffer.toString());
			}//eof if
		}
		return loProceed;
	}//eof logExceutionTime
	
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
	
	private String getTimeText(long aGivenMillis)
	{
		int piMinutes = (int) ((aGivenMillis/60000)%60);
		int piSecoonds = (int) (aGivenMillis/1000)%60;
		long loMillis = aGivenMillis - ((piMinutes*60000) + (piSecoonds*1000));
		String lsTimeTaken = prefixZero(2, String.valueOf(piMinutes)) + COLON
		+ prefixZero(2, String.valueOf(piSecoonds)) + COLON
		+ prefixZero(3, String.valueOf(loMillis)); 
		return lsTimeTaken;
	}//eof getTimeText
	
	private String prefixZero(int aLen, String aData)
	{
		if (aData == null) aData = EMPTY;
		aData = aData.trim();
		int piZeroCount = aLen - aData.length();
		String lsZeroStr = EMPTY;
		if (piZeroCount > 0)
		{
			int piCount = 0;
			while (piCount < piCount)
			{
				lsZeroStr = lsZeroStr + ZERO;
				piCount = piCount + 1;
			}//eof while
		}//eof if
		String lsOutText = lsZeroStr + aData;
		return lsOutText;
	}//eof prefixZero
}
