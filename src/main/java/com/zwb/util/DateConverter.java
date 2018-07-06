package com.zwb.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class DateConverter {
	private static final ConcurrentMap<String, DateTimeFormatter> FORMATTER_CACHE = new ConcurrentHashMap<>();

	private static final int PATTERN_CACHE_SIZE = 500;
	// public static final ZoneId zoneId = ZoneId.systemDefault();
	// public static Date ConverterLocalDateTimeToDate(LocalDateTime datetime) {
	// ZonedDateTime zdatetime = datetime.atZone(zoneId);
	// Instant instant = zdatetime.toInstant();
	// Date date = Date.from(instant);
	// return date;
	// }

	public static String format(LocalDate localDate, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDate.format(formatter);
	}

	public static String format(LocalDateTime localDateTime, String pattern) {
		// DateTimeFormatter formatter = createCacheFormatter(pattern);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}
	
	public static LocalDate parseDate(String localDateStr, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(localDateStr , formatter);
	}

	public static LocalDateTime parseDateTime(String localDateTimeStr, String pattern) {
		// DateTimeFormatter formatter = createCacheFormatter(pattern);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(localDateTimeStr , formatter);
	}

	/**
	 * 在缓存中创建DateTimeFormatter
	 * 
	 * @param pattern
	 *            格式
	 * @return
	 */
	private static DateTimeFormatter createCacheFormatter(String pattern) {
		if (pattern == null || pattern.length() == 0) {
			throw new IllegalArgumentException("Invalid pattern specification");
		}
		DateTimeFormatter formatter = FORMATTER_CACHE.get(pattern);
		if (formatter == null) {
			if (FORMATTER_CACHE.size() < PATTERN_CACHE_SIZE) {
				formatter = DateTimeFormatter.ofPattern(pattern);
				DateTimeFormatter oldFormatter = FORMATTER_CACHE.putIfAbsent(pattern, formatter);
				if (oldFormatter != null) {
					formatter = oldFormatter;
				}
			}
		}

		return formatter;
	}
}
