package com.weather.business.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Util class
 * @author YiDiWang
 *
 */
public class Utility {
	
	public static String dateToEnglishDate(String date) throws Exception{
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE hh:mm aa",Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	String formatDate = simpleDateFormat.format(sdf.parse(date));
    	
		return formatDate;
	}
	
}
