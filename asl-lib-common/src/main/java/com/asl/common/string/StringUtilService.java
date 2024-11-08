package com.asl.common.string;

import java.io.UnsupportedEncodingException;
import java.util.Random;
/** ------------------------------------------------------------------------------------------------------------------------------------
 * Description  : Common string util class to use across all the projects (used for common string functions)
 * Author       : Lithesh Anargha
 * EMail        : LitheshGopal@gmail.com
 * Date         : 19/02/2023
 * Project      : Generic
 * Platform     : ASL
 * Organization : ASL
 * ------------------------------------------------------------------------------------------------------------------------------------**/
public class StringUtilService {
	
	private static final char[] _hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final String FOLDER_SEPARATOR = "/";

    /**
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(final String str1, final String str2)
    {
        if (str1 == null || str2 == null)
        {
            return str1 == str2;
        }
        return str1.equals(str2);
    }

    /**
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equalsIgnoreCase(final String s1, final String s2)
    {
        if (s1 == null || s2 == null)
        {
            return s1 == s2;
        }
        return s1.equalsIgnoreCase(s2);
    }

    /**
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equalsWithTrim(final String s1, final String s2)
    {
        if (s1 == null || s2 == null)
        {
            return s1 == s2;
        }
        return s1.trim().equals(s2.trim());
    }

    /**
     * 
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equalsIgnoreCaseWithTrim(final String s1, final String s2)
    {
        if (s1 == null || s2 == null)
        {
            return s1 == s2;
        }
        return s1.trim().equalsIgnoreCase(s2.trim());
    }

    /**
     * 
     * @param s
     * @return
     */
    public static boolean isEmpty(final String s)
    {
        return trimLength(s) == 0;
    }

    /**
     * 
     * @param s
     * @return
     */
    public static boolean hasContent(final String s)
    {
        return !(trimLength(s) == 0);    // faster than returning !isEmpty()
    }

    /**
     * Use this method when you don't want a length check to
     * throw a NullPointerException when
     *
     * @param s string to return length of
     * @return 0 if string is null, otherwise the length of string.
     */
    public static int length(final String s)
    {
        return s == null ? 0 : s.length();
    }

    /**
     * Returns the length of the trimmed string.  If the length is
     * null then it returns 0.
     */
    public static int trimLength(final String s)
    {
        return (s == null) ? 0 : s.trim().length();
    }

    /**
     * 
     * @param path
     * @param ch
     * @return
     */
    public static int lastIndexOf(String path, char ch)
    {
        if (path == null)
        {
            return -1;
        }
        return path.lastIndexOf(ch);
    }

    // Turn hex String into byte[]
    // If string is not even length, return null.
    /**
     * 
     * @param s
     * @return
     */
    public static byte[] decode(String s)
    {
        final int len = s.length();
        if (len % 2 != 0)
        {
            return null;
        }

        byte[] bytes = new byte[len / 2];
        int pos = 0;

        for (int i = 0; i < len; i += 2)
        {
            byte hi = (byte) Character.digit(s.charAt(i), 16);
            byte lo = (byte) Character.digit(s.charAt(i + 1), 16);
            bytes[pos++] = (byte) (hi * 16 + lo);
        }

        return bytes;
    }

    /**
     * Convert a byte array into a printable format containing a
     * String of hex digit characters (two per byte).
     *
     * @param bytes array representation
     */
    public static String encode(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder(bytes.length << 1);
        for (byte aByte : bytes)
        {
            sb.append(convertDigit(aByte >> 4));
            sb.append(convertDigit(aByte & 0x0f));
        }
        return sb.toString();
    }

    /**
     * Convert the specified value (0 .. 15) to the corresponding hex digit.
     *
     * @param value to be converted
     * @return '0'..'F' in char format.
     */
    private static char convertDigit(int value)
    {
        return _hex[value & 0x0f];
    }

    /**
     * 
     * @param s
     * @param c
     * @return
     */
    public static int count(String s, char c)
    {
        if (isEmpty(s))
        {
            return 0;
        }

        final int len = s.length();
        int count = 0;
        for (int i = 0; i < len; i++)
        {
            if (s.charAt(i) == c)
            {
                count++;
            }
        }

        return count;
    }
    
    /**
     * @param random Random instance
     * @param minLen minimum number of characters
     * @param maxLen maximum number of characters
     * @return String of alphabetical characters, with the first character uppercase (Proper case strings).
     */
    public static String getRandomString(Random random, int minLen, int maxLen)
    {
        StringBuilder s = new StringBuilder();
        final int len = minLen + random.nextInt(maxLen - minLen + 1);
        for (int i=0; i < len; i++)
        {
            s.append(getRandomChar(random, i == 0));
        }
        return s.toString();
    }

    /**
     * 
     * @param random
     * @param upper
     * @return
     */
    public static String getRandomChar(Random random, boolean upper)
    {
        int r = random.nextInt(26);
        return upper ? "" + (char)((int)'A' + r) : "" + (char)((int)'a' + r);
    }
    
    /**
     * Convert a String into a byte[] with a particular encoding.
     * Preferable used when the encoding is one of the guaranteed Java types
     * and you don't want to have to catch the UnsupportedEncodingException
     * required by Java
     *
     * @param s        string to encode into bytes
     * @param encoding encoding to use
     */
    public static byte[] getBytes(String s, String encoding)
    {
        try
        {
            return s == null ? null : s.getBytes(encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException(String.format("Encoding (%s) is not supported by your JVM", encoding), e);
        }
    }


    /**
     * Convert a byte[] into a UTF-8 String.  Preferable used when the encoding
     * is one of the guaranteed Java types and you don't want to have to catch
     * the UnsupportedEncodingException required by Java
     *
     * @param bytes bytes to encode into a string
     */
    public static String createUtf8String(byte[] bytes)
    {
        return createString(bytes, "UTF-8");
    }

    /**
     * Convert a String into a byte[] encoded by UTF-8.
     *
     * @param s        string to encode into bytes
     */
    public static byte[] getUTF8Bytes(String s)
    {
        return getBytes(s, "UTF-8");
    }

    /**
     * Convert a byte[] into a String with a particular encoding.
     * Preferable used when the encoding is one of the guaranteed Java types
     * and you don't want to have to catch the UnsupportedEncodingException
     * required by Java
     *
     * @param bytes    bytes to encode into a string
     * @param encoding encoding to use
     */
    public static String createString(byte[] bytes, String encoding)
    {
        try
        {
            return bytes == null ? null : new String(bytes, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException(String.format("Encoding (%s) is not supported by your JVM", encoding), e);
        }
    }

    /**
     * Convert a byte[] into a UTF-8 encoded String.
     *
     * @param bytes    bytes to encode into a string
     */
    public static String createUTF8String(byte[] bytes)
    {
        return createString(bytes, "UTF-8");
    }

    /**
     * Get the hashCode of a String, insensitive to case, without any new Strings
     * being created on the heap.
     * @param s String input
     * @return int hashCode of input String insensitive to case
     */
    public static int hashCodeIgnoreCase(String s)
    {
        if (s == null)
        {
            return 0;
        }
        final int len = s.length();
        int hash = 0;
        for (int i = 0; i < len; i++)
        {
            hash = 31 * hash + Character.toLowerCase((int)s.charAt(i));
        }
        return hash;
    }


}
