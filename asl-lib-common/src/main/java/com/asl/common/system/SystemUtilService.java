package com.asl.common.system;

import com.asl.common.string.StringUtilService;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common system service class to use across all the projects (used for system functions)
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
public class SystemUtilService {
	
	public static String getExternalVariable(String var)
    {
        String value = System.getProperty(var);
        if (StringUtilService.isEmpty(value))
        {
            value = System.getenv(var);
        }
        return StringUtilService.isEmpty(value) ? null : value;
    }

}
