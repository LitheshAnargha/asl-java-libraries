package com.asl.common.stats.manager;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.asl.common.stats.model.AslStatsModel;

@Service
public class AslMethodStatsManager {
	
	@Autowired
	private com.asl.common.properties.AslCommonProperties AslCommonProperties;
	
	private static final String COLON = ":";
	private static final String COLON_SEPARATOR = " : ";
	private static final String EMPTY = "";
	private static final String LINE_FEED = "\n";
	private static final String PIPE = "|";
	private static final String HYPHEN = "-";
	private static final String SPACE = " ";
	private static final String ZERO = "0";
	private static final String TRUE = "true";
	private static final String YES = "yes";
	private static long lastStatsShownTime = 0l;
	
	private static Map<String,AslStatsModel> ioStatSMap = new ConcurrentHashMap<String,AslStatsModel>();
	private static Logger LOGGER = LoggerFactory.getLogger(AslMethodStatsManager.class);
	
	public AslMethodStatsManager() {
		//aslMethodStatsManager = new AslMethodStatsManager();
	}

	public Map<String,AslStatsModel> getStatisticsMap()
	{
		return ioStatSMap;
	}//eof getStatisticsMap
	
	private String getHeaderToken(int aLen, String aValue, String aDelimeter, boolean isCentreAligned, boolean isLineFeed)
	{
		if (aValue == null) aValue = EMPTY;
		aValue = aValue.trim();
		int piLen = aValue.length();
		int piPaddingSize = aLen - piLen;
		piPaddingSize = piPaddingSize - 2;
		int piLeftPad= piPaddingSize/2;
		int piRightPad = (piPaddingSize-(piPaddingSize/2));
		String lsString = EMPTY;
		
		int piLeftCount = 0;
		while (piLeftCount < piLeftPad)
		{
			lsString = lsString + SPACE;
			piLeftCount = piLeftCount + 1;
		}//eof while
		lsString = lsString + aValue;
		
		int piRightCount = 0;
		while (piRightCount < piRightPad)
		{
			lsString = lsString + SPACE;
			piRightCount = piRightCount + 1;
		}//eof while
		
		if (aDelimeter != null)
		{
			//aDelimeter = aDelimeter.trim();
			lsString = lsString + SPACE;
			lsString = lsString + aDelimeter;
		}//eof if
		if (isLineFeed)
		{
			lsString = lsString + LINE_FEED;
		}//eof if
		return lsString;
	}//eof getHeaderToken
	
	private String getHeaderToken(int aLen, String aValue, String aDelimeter, boolean isLineFeed)
	{
		if (aValue == null) aValue = EMPTY;
		aValue = aValue.trim();
		int piLen = aValue.length();
		int piPaddingSize = aLen - piLen;
		piPaddingSize = piPaddingSize - 2;
		String lsString = aValue;
		
		int piCount = 0;
		while (piCount < piPaddingSize)
		{
			lsString = lsString + SPACE;
			piCount = piCount + 1;
		}//eof while
		
		if (aDelimeter != null)
		{
			//aDelimeter = aDelimeter.trim();
			lsString = lsString + SPACE;
			lsString = lsString + aDelimeter;
		}//eof if
		
		if (isLineFeed)
		{
			lsString = lsString + LINE_FEED;
		}//eof if
		return lsString;
	}//eof getHeaderToken
	
	private String getHeaderToken(int aLen, boolean isFirst, String aValue, String aDelimeter, boolean isLineFeed)
	{
		if (aValue == null) aValue = EMPTY;
		aValue = aValue.trim();
		if (isFirst)
		{
			aValue = aDelimeter + aValue;
		}//eof if
		
		int piLen = aValue.length();
		int piPaddingSize = aLen - piLen;
		piPaddingSize = piPaddingSize - 2;
		String lsString = aValue;
		
		int piCount = 0;
		while (piCount < piPaddingSize)
		{
			lsString = lsString + SPACE;
			piCount = piCount + 1;
		}//eof while
		
		if (aDelimeter != null)
		{
			//aDelimeter = aDelimeter.trim();
			lsString = lsString + SPACE;
			lsString = lsString + aDelimeter;
		}//eof if
		
		if (isLineFeed)
		{
			lsString = lsString + LINE_FEED;
		}//eof if
		return lsString;
	}//eof getHeaderToken
	
	private String getHeaderLine(int aLen,  String aChar, boolean isLineFeed)
	{
		String lsString = EMPTY;
		int piCount = 0;
		while (piCount < aLen)
		{
			//aChar = aChar.trim();
			lsString = lsString + aChar;
			piCount = piCount + 1;
		}//eof while
		
		if (isLineFeed)
		{
			lsString = lsString + LINE_FEED;
		}//eof if
		return lsString;
	}//eof getHeaderLine
	
	private String trimToSize(String aValue, int aLen)
	{
		if (aValue == null) aValue = EMPTY;
		aValue = aValue.trim();
		if (aValue.length() > aLen)
		{
			aValue = aValue.substring(0, aLen-1);
		}//eof if
		return aValue;
	}//eof trimToSize
	
	private String getTimeText(long aTimeInMillis)
	{
		int piMinutes = (int) ((aTimeInMillis/60000)%60);
		int piSeconds = (int) (aTimeInMillis/1000)%60;
		int piMillis = (int) aTimeInMillis - ((piMinutes*60000)+(piSeconds*1000));
		String lsTimeText = prefixWithZero(2, String.valueOf(piMinutes)) + COLON 
		+ prefixWithZero(2, String.valueOf(piSeconds)) + COLON 
		+ prefixWithZero(3, String.valueOf(piMillis));
		return lsTimeText;
	}//eof getTimeText
	
	private String prefixWithZero(int aLen, String aValue)
	{
		if (aValue == null) aValue = EMPTY;
		aValue = aValue.trim();
		int piZerosCount = aLen - aValue.length();
		String lsLeadingZeros = EMPTY;
		if (piZerosCount > 0)
		{
			int piCount = 0;
			while (piCount < piZerosCount)
			{
				//aChar = aChar.trim();
				lsLeadingZeros = lsLeadingZeros + ZERO;
				piCount = piCount + 1;
			}//eof while
		}//eof if
		String lsNewText = lsLeadingZeros + aValue;
		return lsNewText;
	}//eof prefixWithZero
	
	private boolean isReadyToShowStatistics()
	{
		if (AslCommonProperties == null) return false;
		boolean lbStatus = false;
		int piMonitoringFrequency = AslCommonProperties.statsMonitoringFrequency;
		long piCurrentTime = System.currentTimeMillis();
		
		if (lastStatsShownTime == 0l)
		{
			lastStatsShownTime = piCurrentTime;
			lbStatus = true;
		}
		
		if (lastStatsShownTime < (piCurrentTime-piMonitoringFrequency))
		{
			lastStatsShownTime = piCurrentTime;
			lbStatus = true;
		}
		return lbStatus;
	}//eof isReadyToShowStatistics
	
	private boolean isStatMonitoringEnabled()
	{
		if (AslCommonProperties == null) return false;
		String lbStatsEnabled = AslCommonProperties.isStatsMonitoringEnabled;
		if (lbStatsEnabled == null) lbStatsEnabled = EMPTY;
		lbStatsEnabled = lbStatsEnabled.trim();
		if (lbStatsEnabled.equalsIgnoreCase(TRUE)) return true;
		if (lbStatsEnabled.equalsIgnoreCase(YES)) return true;
		return false;
	}//eof isStatMonitoringEnabled
	
	private void logMessage(String aMessage)
	{
		String lsMessage = this.getClass().getName() + COLON_SEPARATOR + aMessage;
		LOGGER.info(lsMessage);
	}//eof logMessage
	
	/**
	 * 
	 * @param aKey
	 * @param aStopWatch
	 */
	public void setTimeTaken(String aKey, StopWatch aStopWatch)
	{
		if (!isStatMonitoringEnabled()) return;
		if (aStopWatch == null) return;
		if (aStopWatch.isRunning()) aStopWatch.stop();
		
		long piTimeTaken = aStopWatch.getTotalTimeMillis();
		AslStatsModel loAslStatsModel = null;
		if (ioStatSMap.containsKey(aKey))
		{
			loAslStatsModel = ioStatSMap.get(aKey);
		}
		else
		{
			loAslStatsModel = new AslStatsModel();
			ioStatSMap.put(aKey, loAslStatsModel);
		}//eof if
		if (loAslStatsModel.getMaxTime() == 0l) loAslStatsModel.setMaxTime(piTimeTaken);
		if (loAslStatsModel.getMinTime() == 0l) loAslStatsModel.setMinTime(piTimeTaken);
		if (loAslStatsModel.getMaxTime() > piTimeTaken) loAslStatsModel.setMaxTime(piTimeTaken);
		if (loAslStatsModel.getMinTime() < piTimeTaken) loAslStatsModel.setMinTime(piTimeTaken);
		loAslStatsModel.updateTotalTime(piTimeTaken);
	}//eof setTimeTaken
	
	
	public void displayMethodStatistics()
	{
		boolean lbStatsEnabled = isStatMonitoringEnabled();
		boolean lbShowingEnabled = isReadyToShowStatistics();
		
		//logMessage("lbStatsEnabled   -> "+lbStatsEnabled);
		//logMessage("lbShowingEnabled -> "+lbShowingEnabled);
		
		if (lbStatsEnabled == false) return;
		if (lbShowingEnabled == false) return;

		StringBuffer BUFFER = new StringBuffer();
		BUFFER.append(LINE_FEED);
		BUFFER.append(LINE_FEED);
		BUFFER.append(LINE_FEED);
		BUFFER.append(LINE_FEED);
		
		BUFFER.append(getHeaderLine(200, HYPHEN, true));
		BUFFER.append(getHeaderToken(5, true, "No", PIPE, false));
		BUFFER.append(getHeaderToken(120, "Item", PIPE, false));
		BUFFER.append(getHeaderToken(25, "Min Value (mm:ss:sss)", PIPE, true, false));
		BUFFER.append(getHeaderToken(25, "Max Value (mm:ss:sss)", PIPE, true, false));
		BUFFER.append(getHeaderToken(25, "Avg Value (mm:ss:sss)", PIPE, true, false));
		BUFFER.append(LINE_FEED);
		BUFFER.append(getHeaderLine(200, HYPHEN, true));
		
		Iterator<String> loKeyIter = ioStatSMap.keySet().iterator();
		int piCount = 1;
		while (loKeyIter.hasNext())
		{
			String lsKey = loKeyIter.next();
			AslStatsModel loAslStatsModel = ioStatSMap.get(lsKey);
			long piMaxTime = loAslStatsModel.getMaxTime();
			long piMinTime = loAslStatsModel.getMinTime();
			long piAvgTime = loAslStatsModel.getAvgTime();
			
			BUFFER.append(getHeaderToken(5, true, String.valueOf(piCount), PIPE, false));
			BUFFER.append(getHeaderToken(120, trimToSize(lsKey,100), PIPE, false));
			//BUFFER.append(getHeaderToken(120, trimToSize(lsKey,100), PIPE, false));
			BUFFER.append(getHeaderToken(25, getTimeText(piMaxTime), PIPE, true, false));
			BUFFER.append(getHeaderToken(25, getTimeText(piMinTime), PIPE, true, false));
			BUFFER.append(getHeaderToken(25, getTimeText(piAvgTime), PIPE, true, true));
			BUFFER.append(getHeaderLine(200, HYPHEN,true));
			piCount = piCount + 1;
		}//eof while
		
		BUFFER.append(getHeaderToken(200, true, "[mm:ss:sss stands for Minutes:Seconds:Milliseconds respectively!]", PIPE, true));
		BUFFER.append(getHeaderLine(200, HYPHEN,true));
		BUFFER.append(LINE_FEED);
		BUFFER.append(LINE_FEED);
		BUFFER.append(LINE_FEED);
		BUFFER.append(LINE_FEED);
		
		logMessage(BUFFER.toString());
		
	}//eof displayMethodStatistics

}
