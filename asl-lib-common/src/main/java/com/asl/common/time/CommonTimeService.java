package com.asl.common.time;

import org.springframework.stereotype.Service;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common time util class to use across all the projects (used for common time functions)
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
@Service
public class CommonTimeService {
	
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
	public static final long THOUSAND = 1000L;
	
	
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
