package com.asl.common.crypto;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

import javax.crypto.Cipher;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common Crypto Service class which can be extended by other classes in this package
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
public class CommonCryptoService {
	
	@SuppressWarnings({ "rawtypes", "unchecked","unused" })
	public static void fixKeyLength() 
	{
	    String errorString = "Failed manually overriding key-length permissions.";
		int newMaxKeyLength;
	    try 
	    {
	        if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) 
	        {
	            Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
	            
				Constructor con = c.getDeclaredConstructor();
	            con.setAccessible(true);
	            Object allPermissionCollection = con.newInstance();
	            Field f = c.getDeclaredField("all_allowed");
	            f.setAccessible(true);
	            f.setBoolean(allPermissionCollection, true);

	            c = Class.forName("javax.crypto.CryptoPermissions");
	            con = c.getDeclaredConstructor();
	            con.setAccessible(true);
	            Object allPermissions = con.newInstance();
	            f = c.getDeclaredField("perms");
	            f.setAccessible(true);
	            ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

	            c = Class.forName("javax.crypto.JceSecurityManager");
	            f = c.getDeclaredField("defaultPolicy");
	            f.setAccessible(true);
	            Field mf = Field.class.getDeclaredField("modifiers");
	            mf.setAccessible(true);
	            mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	            f.set(null, allPermissions);

	            newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
	        }//eof if
	    } 
	    catch (Exception aException) 
	    {
	    	aException.printStackTrace();
	        throw new RuntimeException(errorString, aException);
	    }
	}//eof fixKeyLength

}
