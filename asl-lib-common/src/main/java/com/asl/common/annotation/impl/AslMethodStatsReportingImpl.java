package com.asl.common.annotation.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AslMethodStatsReportingImpl {
	
	private static final String COLON = ":";
	private static final String METHOD_SIGN = " ()";
	private static final String EXCEPTION_TEXT = "{Exception Flow}";
	
	@Autowired
	private com.asl.common.stats.manager.AslMethodStatsManager AslMethodStatsManager;
	
	@Around("@annotation(com.asl.common.annotation.AslMethodStatsReporting)")
	public Object showMethodLevelStatistics(ProceedingJoinPoint aJoinPoint) throws Throwable
	{
		StopWatch loStopWatch = null;
		Object loProceed = null;
		Object loObject = aJoinPoint.getTarget();
		Signature loSignature = aJoinPoint.getSignature();
		String lsMethodName = loSignature.getName();
		String lsSimpleName = loObject.getClass().getSimpleName();
		try
		{
			loStopWatch = new StopWatch();
			loStopWatch.start();
			loProceed = aJoinPoint.proceed();
		}
		catch(Exception aException)
		{
			loStopWatch.stop();
			String lsKey = lsSimpleName + COLON + lsMethodName + METHOD_SIGN + EXCEPTION_TEXT;
			AslMethodStatsManager.setTimeTaken(lsKey, loStopWatch);
			AslMethodStatsManager.displayMethodStatistics();
			aException.printStackTrace();
			throw aException;
		}
		if (loStopWatch.isRunning())
		{
			loStopWatch.stop();
			String lsKey = lsSimpleName + COLON + lsMethodName + METHOD_SIGN;
			AslMethodStatsManager.setTimeTaken(lsKey, loStopWatch);
			AslMethodStatsManager.displayMethodStatistics();
		}
		return loProceed;
	}//eof showMethodLevelLogging

}
