package com.asl.common.properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common configuration class across all the projects (used for common properties)
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
@Component
@EnableConfigurationProperties
@ConfigurationProperties
public class AslCommonProperties {
	
	
	@Value("${asl.common.logging.top.header.char:,}")
	public String topHeaderChar;
	
	@Value("${asl.common.logging.bottom.header.char:'}")
	public String bottomHeaderChar;
	
	@Value("${asl.common.method.logger.botom.header.char:,}")
	public String methodloggerHeaderChar;
	
	@Value("${asl.common.logging.enabled:#{false}}")
	public boolean loggingEnabled;
	
	@Value("${asl.common.logging.sop.enabled:#{false}}")
	public boolean loggingSopEnabled;
	
	@Value("${asl.common.logging.stats.monitoring.frequency.in.millis:#{1000}}")
	public int statsMonitoringFrequency;
	
	@Value("${asl.common.logging.stats.monitoring.enabled:#{true}}")
	public String isStatsMonitoringEnabled;
	
	

}
