package org.edf.hifox.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @author WangYang
 *
 */
public class DateUtil {
	public static Date parseDate(String str, String... parsePatterns) {
		try {
			return DateUtils.parseDate(str, parsePatterns);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String addDays(String str, String parsePattern, int day) {
		Date date = DateUtils.addDays(DateUtil.parseDate(str, parsePattern), day);
		return DateFormatUtils.format(date, parsePattern);
	}
	
}
