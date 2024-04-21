package com.asl.common.message;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common Message Service class which can be used by other classes across all the projects (resource bundle operations)
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
@Service
public class CommonMessageService {
	
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

	/**
	 * 
	 * @param aMessageFileName
	 * @param aMessageKey
	 * @param aLocale
	 * @param aValues
	 * @return
	 */
	public String getValueFromResourceBundle(final String aMessageFileName, final String aMessageKey, 
	final Locale aLocale, final List<Object> aValues)
	{
		String lsMessageValue = EMPTY;
		Locale loGivenLocale = aLocale;
		if (loGivenLocale == null) loGivenLocale = ENGLISH_LOCALE;
		ResourceBundle loResourceBundle = ResourceBundle.getBundle(aMessageFileName, loGivenLocale);
		lsMessageValue = loResourceBundle.getString(aMessageKey);
		if (aValues != null)
		{
			MessageFormat loMessageFormat = new MessageFormat(lsMessageValue, loGivenLocale);
			lsMessageValue = loMessageFormat.format(aValues.toArray());
		}//eof if
		return lsMessageValue;
	}//eof getValueFromResourceBundle
	/**
	 * 
	 * @param aMessageFileName
	 * @param aMessageKey
	 * @param aValues
	 * @return
	 */
	public String getValueFromResourceBundle(final String aMessageFileName, final String aMessageKey, 
	final List<Object> aValues)
	{
		String lsMessageValue = EMPTY;
		Locale loGivenLocale = ENGLISH_LOCALE;
		ResourceBundle loResourceBundle = ResourceBundle.getBundle(aMessageFileName, loGivenLocale);
		lsMessageValue = loResourceBundle.getString(aMessageKey);
		if (aValues != null)
		{
			MessageFormat loMessageFormat = new MessageFormat(lsMessageValue, loGivenLocale);
			lsMessageValue = loMessageFormat.format(aValues.toArray());
		}//eof if
		return lsMessageValue;
	}//eof getValueFromResourceBundle
	/**
	 * 
	 * @param aMessageKey
	 * @param aValues
	 * @return
	 */
	public String getValueFromResourceBundle(final String aMessageKey, final List<Object> aValues)
	{
		String lsMessageValue = EMPTY;
		Locale loGivenLocale = ENGLISH_LOCALE;
		ResourceBundle loResourceBundle = ResourceBundle.getBundle(MESSAGES_FILE, loGivenLocale);
		lsMessageValue = loResourceBundle.getString(aMessageKey);
		if (aValues != null)
		{
			MessageFormat loMessageFormat = new MessageFormat(lsMessageValue, loGivenLocale);
			lsMessageValue = loMessageFormat.format(aValues.toArray());
		}//eof if
		return lsMessageValue;
	}//eof getValueFromResourceBundle
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
}//eof class
