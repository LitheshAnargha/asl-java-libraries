package com.asl.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asl.common.properties.AslCommonProperties;
import com.asl.common.constants.AslCommonBeanNameConstants;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common  util class to use across all the projects (used for common util functions)
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
@Service(AslCommonBeanNameConstants.ASL_COMMON_UNTIL_SERVICE)
public class CommonUtilService {
	
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
	public static final long THOUSAND = 1000L;
	
	@Autowired
	private AslCommonProperties aslCommonProperties;

	/**
	 * 
	 * @param aStringValue
	 * @param aLength
	 * @return
	 */
	public String getRightAligedString(final String aStringValue, final int aLength)
	{
		String lsValue = getAsString(aStringValue);
		int piLength = lsValue.length();
		int piLenToPadd = (aLength-piLength);
		int count = 0;
		String lsPaddingBuffer = EMPTY;
		while (count < piLenToPadd)
		{
			lsPaddingBuffer = lsPaddingBuffer + SPACE;
			count = count+1;
		}//eof while 
		String lsPaddedString = lsPaddingBuffer+lsValue;
		return lsPaddedString;
	}//eof getRightAligedString
	/**
	 * 
	 * @param aStringValue
	 * @param aLength
	 * @return
	 */
	public String getLeftAligedString(final String aStringValue, final int aLength)
	{
		String lsValue = getAsString(aStringValue);
		int piLength = lsValue.length();
		int piLenToPadd = (aLength-piLength);
		int count = 0;
		String lsPaddingBuffer = EMPTY;
		while (count < piLenToPadd)
		{
			lsPaddingBuffer = lsPaddingBuffer + SPACE;
			count = count+1;
		}//eof while 
		String lsPaddedString = lsValue+lsPaddingBuffer;
		return lsPaddedString;
	}//eof getLeftAligedString
	/**
	 * 
	 * @param aStringValue
	 * @param aLength
	 * @return
	 */
	public String getCentralAligedString(final String aStringValue, final int aLength)
	{
		String lsValue = getAsString(aStringValue);
		int piLength = lsValue.length();
		int piLenToPadd = (aLength-piLength)/2;
		int count = 0;
		String lsPaddingBuffer = EMPTY;
		while (count < piLenToPadd)
		{
			lsPaddingBuffer = lsPaddingBuffer + SPACE;
			count = count+1;
		}//eof while 
		String lsPaddedString = lsPaddingBuffer+lsValue+lsPaddingBuffer;
		return lsPaddedString;
	}//eof getCentralAligedString
	
	/**
	 * 
	 * @param aChar
	 * @param aLength
	 * @return
	 */
	public String getHeader(final String aChar, final int aLength)
	{
		StringBuffer loBuffer = new StringBuffer();
		loBuffer.append(LINE_FEED);
		int count = 0;
		while (count <= aLength)
		{
			loBuffer.append(aChar);
			count = count + 1;
		}//eof while
		loBuffer.append(LINE_FEED);
		return loBuffer.toString();
	}//eof getHeader
	
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public String getAsString (final String aValue)
	{
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return EMPTY;
		String lsValue = aValue.trim();
		return lsValue;
	}//eof getAsInteger
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public Integer getAsInteger (final String aValue)
	{
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return null;
		String lsValue = aValue.trim();
		Integer loIntegerValue = Integer.parseInt(lsValue);
		return loIntegerValue;
	}//eof getAsInteger
	
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public Long getAsLong(final String aValue)
	{
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return null;
		String lsValue = aValue.trim();
		Long loLongValue = Long.parseLong(lsValue);
		return loLongValue;
	}//eof getAsLong
	
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public boolean getAsBooleanValue(final String aValue) 
	{
		String lsValue = aValue;
		if (lsValue == null) lsValue = EMPTY;
		lsValue = lsValue.toLowerCase();
		lsValue = lsValue.trim();
		if (lsValue.equals(YES)) return true;
		if (lsValue.equals(TRUE)) return true;
		return false;
	}//eof getAsBooleanValue
	
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public boolean isEnabled(final String aValue)
	{
		boolean lbStatus = getAsBooleanValue(aValue);
		return lbStatus;
	}//eof isEnabled
	
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public boolean isDisabled(final String aValue)
	{
		boolean lbStatus = getAsBooleanValue(aValue);
		lbStatus = !lbStatus;
		return lbStatus;
	}//eof isDisabled
	
	/**
	 * 
	 * @param aValue
	 * @return
	 */
	public boolean isEmpty(final String aValue) 
	{
		String lsValue = aValue;
		if (lsValue == null) lsValue = EMPTY;
		lsValue = lsValue.trim();
		if (lsValue.length()>0) return false;
		return true;
	}//eof getAsBooleanValue
	/**
	 * 
	 * @param aValue
	 * @param aTokenizer
	 * @return
	 */
	public List<String> getAsStringList(final String aValue, final String aSplitter)
	{
		List<String> loStringList = new ArrayList<String>();
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return loStringList;
		loStringList = Collections.list(new StringTokenizer(aValue, aSplitter)).stream()
	    .map(token -> (String) token)
	    .collect(Collectors.toList());
		return loStringList;
	}//eof getAsStringList
	/**
	 * 
	 * @param aValue
	 * @param aSplitter
	 * @param aTokenizer
	 * @return
	 */
	public Map<String,String> getAsStringMap(final String aValue, final String aSplitter, 
	final String aTokenizer)
	{
		Map<String, String> loStringMap = new HashMap<String,String>();
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return loStringMap;
		StringTokenizer loSplitter = new StringTokenizer(aValue, aSplitter);
	    while (loSplitter.hasMoreElements()) 
	    {
	    	String lsToken = loSplitter.nextToken();
	    	boolean lbTokenEmpty = isEmpty(lsToken);
	    	if (lbTokenEmpty) continue;
	    	List<String> loList = getAsStringList(lsToken,aTokenizer);
	    	if (loList.size() == 2)
	    	{
		    	String lsKey = loList.get(0);
		    	String lsValue = loList.get(1);
		    	loStringMap.put(lsKey, lsValue);
	    	}//eof if
	    }//eof while
		return loStringMap;
	}//eof getAsStringMap
	/**
	 * 
	 * @param aValue
	 * @param aTokenizer
	 * @return
	 */
	public List<Integer> getAsIntegerList(final String aValue, final String aSplitter)
	{
		List<Integer> loIntegerList = new ArrayList<Integer>();
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return loIntegerList;
		loIntegerList = Collections.list(new StringTokenizer(aValue, aSplitter)).stream()
		.map(token -> (Integer) Integer.parseInt((String) token))
		.collect(Collectors.toList());
		return loIntegerList;
	}//eof getAsIntegerList
	/**
	 * 
	 * @param aValue
	 * @param aSplitter
	 * @param aTokenizer
	 * @return
	 */
	public Map<String,Integer> getAsIntegerMap(final String aValue, final String aSplitter, 
	final String aTokenizer)
	{
		Map<String, Integer> loIntegerMap = new HashMap<String,Integer>();
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return loIntegerMap;
		StringTokenizer loSplitter = new StringTokenizer(aValue, aSplitter);
	    while (loSplitter.hasMoreElements()) 
	    {
	    	String lsToken = loSplitter.nextToken();
	    	boolean lbTokenEmpty = isEmpty(lsToken);
	    	if (lbTokenEmpty) continue;
	    	List<String> loList = getAsStringList(lsToken,aTokenizer);
	    	if (loList.size() == 2)
	    	{
		    	String lsKey = loList.get(0);
		    	String lsValue = loList.get(1);
		    	Integer loIntegerValue = Integer.parseInt(lsValue) ;
		    	loIntegerMap.put(lsKey, loIntegerValue);
	    	}//eof if
	    }//eof while
		return loIntegerMap;
	}//eof getAsIntegerMap
	/**
	 * 
	 * @param aValue
	 * @param aTokenizer
	 * @return
	 */
	public List<Long> getAsLongList(final String aValue, final String aSplitter)
	{
		List<Long> loLongList = new ArrayList<Long>();
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return loLongList;
		loLongList = Collections.list(new StringTokenizer(aValue, aSplitter)).stream()
		.map(token -> (Long) Long.parseLong((String) token))
		.collect(Collectors.toList());
		return loLongList;
	}//eof getAsLongList
	
	/**
	 * 
	 * @param aValue
	 * @param aSplitter
	 * @param aTokenizer
	 * @return
	 */
	public Map<String,Long> getAsLongMap(final String aValue, final String aSplitter, 
	final String aTokenizer)
	{
		Map<String, Long> loLongMap = new HashMap<String,Long>();
		boolean lbEmpty = isEmpty(aValue);
		if (lbEmpty) return loLongMap;
		StringTokenizer loSplitter = new StringTokenizer(aValue, aSplitter);
	    while (loSplitter.hasMoreElements()) 
	    {
	    	String lsToken = loSplitter.nextToken();
	    	boolean lbTokenEmpty = isEmpty(lsToken);
	    	if (lbTokenEmpty) continue;
	    	List<String> loList = getAsStringList(lsToken,aTokenizer);
	    	if (loList.size() == 2)
	    	{
		    	String lsKey = loList.get(0);
		    	String lsValue = loList.get(1);
		    	Long loLongValue = Long.parseLong(lsValue);
		    	loLongMap.put(lsKey, loLongValue);
	    	}//eof if
	    }//eof while
		return loLongMap;
	}//eof getAsLongMap
	
	/**
	 * 
	 * @param aMinutes
	 * @return
	 */
	public long getTimeInMillisBeforeMinutes(int aMinutes) 
	{
		long loCurrentTime = System.currentTimeMillis();
		long loMillis = aMinutes * THOUSAND;
		long loTimeBeforeMinutes = loCurrentTime - loMillis;
		return loTimeBeforeMinutes;
	}//eof getTimeInMillisBeforeMinutes
	/**
	 * 
	 * @param aMillis
	 * @return
	 */
	public long getTimeInMillisBeforeMillis(int aMillis) 
	{
		long loCurrentTime = System.currentTimeMillis();
		long loTimeBeforeMinutes = loCurrentTime - aMillis;
		return loTimeBeforeMinutes;
	}//eof  getTimeInMillisBeforeMillis
	
	/**
	 * 
	 * @param aListString
	 * @param anIndex
	 * @return
	 */
	public String getStringFromList(List<String> aListString, int anIndex)
	{
		int liCurrentIndex = anIndex;
		int liSize = aListString.size();
		liSize = liSize - 1;
		if (liCurrentIndex > liSize) liCurrentIndex = 0;
		String lsValue = aListString.get(liCurrentIndex);
		lsValue = lsValue.trim();
		return lsValue;
	}//eof getStringFromList
	
	/**
	 * 
	 * @param aListString
	 * @param anIndex
	 * @return
	 */
	public Integer getIntegerFromList(List<String> aListString, int anIndex)
	{
		int liCurrentIndex = anIndex;
		int liSize = aListString.size();
		liSize = liSize - 1;
		if (liCurrentIndex > liSize) liCurrentIndex = 0;
		String lsValue = aListString.get(liCurrentIndex);
		Integer loIntValue = Integer.parseInt(lsValue.trim());
		return loIntValue;
	}//eof getIntegerFromList
	
	/**
	 * 
	 * @param aObjectList
	 * @return
	 */
	public boolean isEmptyList(List<String> aStringList)
	{
		if (aStringList == null) return true;
		if (aStringList.size()==0) return true;
		return false;
	}//eof isEmptyList
	
	/**
	 * 
	 * @param aStringList
	 * @param aIndex
	 * @return
	 */
	public String getValueFromList(List<String> aStringList, int aIndex)
	{
		String lsValue = EMPTY;
		if (aStringList == null) return lsValue;
		if (aStringList.size()==0) return lsValue;
		int liSize = aStringList.size();
		liSize = liSize - 1;
		if (aIndex > liSize)
		{
			lsValue = aStringList.get(0);
		}
		else
		{
			lsValue = aStringList.get(aIndex);
		}//eof if
		lsValue = lsValue.trim();
		return lsValue;
	}//eof getValueFromList
	
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
		boolean lsLoggingEnabled = false;
		try
		{
			lsLoggingEnabled = aslCommonProperties.loggingEnabled; 
			boolean lsSopLoggingEnabled = aslCommonProperties.loggingSopEnabled;
			
			if (lsSopLoggingEnabled == true) 
			{
				System.out.println(aMessage);
				return false;
			}//eof if
		}
		catch (Exception aException)
		{
			aException.printStackTrace();
			//During shutdown ApplicationContext may not be accessible hence return true! 
			return false;
		}
		return lsLoggingEnabled;
	}//eof isLoggerLoggingApplicabale
	
	/**
	 * 
	 * @param aObject1
	 * @param aObject2
	 * @return
	 */
	public List<Object> getAsList(Object aObject1, Object aObject2)
	{
		List<Object> loObjectList = new LinkedList<Object>();
		loObjectList.add(aObject1);
		loObjectList.add(aObject2);
		return loObjectList;
	}//eof getAsList
	
	/**
	 * 
	 * @param aObject1
	 * @param aObject2
	 * @param aObject3
	 * @return
	 */
	public List<Object> getAsList(Object aObject1, Object aObject2, Object aObject3)
	{
		List<Object> loObjectList = new LinkedList<Object>();
		loObjectList.add(aObject1);
		loObjectList.add(aObject2);
		loObjectList.add(aObject3);
		return loObjectList;
	}//eof getAsList
	
	/**
	 * 
	 * @param aObject1
	 * @param aObject2
	 * @param aObject3
	 * @param aObject4
	 * @return
	 */
	public List<Object> getAsList(Object aObject1, Object aObject2, Object aObject3, Object aObject4)
	{
		List<Object> loObjectList = new LinkedList<Object>();
		loObjectList.add(aObject1);
		loObjectList.add(aObject2);
		loObjectList.add(aObject3);
		loObjectList.add(aObject4);
		return loObjectList;
	}//eof getAsList
	
	/**
	 * 
	 * @param aObject1
	 * @param aObject2
	 * @param aObject3
	 * @param aObject4
	 * @param aObject5
	 * @return
	 */
	public List<Object> getAsList(Object aObject1, Object aObject2, Object aObject3, Object aObject4, Object aObject5)
	{
		List<Object> loObjectList = new LinkedList<Object>();
		loObjectList.add(aObject1);
		loObjectList.add(aObject2);
		loObjectList.add(aObject3);
		loObjectList.add(aObject4);
		loObjectList.add(aObject5);
		return loObjectList;
	}//eof getAsList
}//eof class
