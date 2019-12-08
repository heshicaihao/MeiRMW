package com.heshicai.meirmw.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTime {

	public static String getTime(Long time) {
		if (time == null) {
			time = System.currentTimeMillis();
		}
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date d1 = new Date(time);
		String t1 = format.format(d1);
		return t1;
	}

}
