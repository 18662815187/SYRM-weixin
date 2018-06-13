package com.lwyykj.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
	public static int formatDate(String time,String style) {
		int result = 0;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
		Date date = null;
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = (int) (date.getTime() / 1000);
		return result;
	}
}
