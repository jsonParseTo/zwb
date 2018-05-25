package com.zwb.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateConverter {

	public static final ZoneId zoneId = ZoneId.systemDefault();
	public static Date ConverterLocalDateTimeToDate(LocalDateTime datetime) {
		ZonedDateTime zdatetime = datetime.atZone(zoneId);
		Instant instant = zdatetime.toInstant();
		Date date = Date.from(instant);
		return date;
	}
}
