package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class TimeUtil {
	/**
	 * format yyyy-MM-dd to yyyyMMdd
	 * @param str
	 * @return
	 */
	public static String format10DateStrTo8Str(String str) {
		if (str != null && str.length() == 10) {
        	return str.substring(0, 4) + str.substring(5, 7) + str.substring(8, 10);
		} else {
			return str;
		}
	}
	
	/**
	 * format yyyyMMdd to yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static String format8StrTo10DateStr(String str) {
		if (str != null && str.length() == 8) {
        	return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
		} else {
			return str;
		}
	}
	
	/**
	 * format HHmmss to HH:mm:ss
	 * @param str
	 * @return
	 */
	public static String format6StrTo8TimeStr(String str) {
		if (str != null && str.length() == 6) {
        	return str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6);
		} else {
			return str;
		}
	}
	
	/**
	 * format yyyyMMddHHmmss to yyyy-MM-dd HH:mm:ss
	 * @param str
	 * @return
	 */
	public static String format14StrTo19TimeStr(String str) {
		if (str != null && str.length() == 14) {
        	return format8StrTo10DateStr(str.substring(0, 8)) + " " + format6StrTo8TimeStr(str.substring(8, 14));
		} else {
			return str;
		}
	}
	
	/**
	 * format yyyyMMddHHmmss.sss to yyyy-MM-dd HH:mm:ss.sss
	 * @param str
	 * @return
	 */
	public static String format18StrTo23TimeStr(String str) {
		if (str != null && str.length() == 18) {
        	return format8StrTo10DateStr(str.substring(0, 8)) + " " + format6StrTo8TimeStr(str.substring(8, 14)) + str.substring(14);
		} else {
			return str;
		}
	}
	
	public static String get32Guid() {
        String uuid = UUID.randomUUID().toString(); 
        return uuid.substring(0, 32);
	}
	
	public static String getCur8Date() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
	}
	
	public static String getCur10Date() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        return format.format(new Date());
	}
	
	public static String getCur6Time() {
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        return format.format(new Date());
	}
	
	public static String getCur14Time() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
	}	
	
	public static String getCur19Time() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
        return format.format(new Date());
	}
	
	public static String getCurHour() {
        SimpleDateFormat format = new SimpleDateFormat("HH");
        return format.format(new Date());
	}
}
