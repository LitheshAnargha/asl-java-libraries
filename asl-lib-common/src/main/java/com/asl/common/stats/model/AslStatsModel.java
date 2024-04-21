package com.asl.common.stats.model;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

public class AslStatsModel {
	
	private long minTime = 0l;
	private long maxTime = 0l;
	private long avgTime = 0l;
	private BigInteger totoalRequest = BigInteger.ZERO;
	private BigInteger totoalTime = BigInteger.ZERO;
	
	public void updateTotalTime(long aTime) 
	{
		totoalRequest = totoalRequest.add(BigInteger.ONE);
		totoalTime = totoalTime.add(new BigInteger(String.valueOf(aTime)));
		DecimalFormat loDecimalFormat = new DecimalFormat("000");  
		try 
		{
			avgTime = Long.parseLong(loDecimalFormat.parse(totoalTime.divide(totoalRequest).toString()).toString());
		} 
		catch (NumberFormatException aNumberFormatException) 
		{
			aNumberFormatException.printStackTrace();
		} 
		catch (ParseException aParseException) 
		{
			aParseException.printStackTrace();
		}
	}//eof updateTotalTime
	
	public long getAverageTime()
	{
		return Long.parseLong(totoalTime.divide(totoalRequest).toString());
	}//eof getAverageTime

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(long avgTime) {
		this.avgTime = avgTime;
	}

	public BigInteger getTotoalRequest() {
		return totoalRequest;
	}

	public void setTotoalRequest(BigInteger totoalRequest) {
		this.totoalRequest = totoalRequest;
	}

	public BigInteger getTotoalTime() {
		return totoalTime;
	}

	public void setTotoalTime(BigInteger totoalTime) {
		this.totoalTime = totoalTime;
	}

	@Override
	public String toString() {
		return "AslStatsModel [minTime=" + minTime + ", maxTime=" + maxTime + ", avgTime=" + avgTime
				+ ", totoalRequest=" + totoalRequest + ", totoalTime=" + totoalTime + "]";
	}
}
