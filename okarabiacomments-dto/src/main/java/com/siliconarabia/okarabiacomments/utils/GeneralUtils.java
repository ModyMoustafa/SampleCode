package com.siliconarabia.okarabiacomments.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GeneralUtils {

	public static Date getCurrentTimeGMT() {

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long time = c.getTimeInMillis();
		long offset = TimeZone.getDefault().getOffset(time);
		return new Date(time - offset);

	}

}
