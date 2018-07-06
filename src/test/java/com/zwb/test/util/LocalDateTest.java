package com.zwb.test.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import com.zwb.util.DateConverter;


public class LocalDateTest {
	@Test
	public void testLocalDateFormat(){
		LocalDate localDate = LocalDate.now();
		String date = DateConverter.format(localDate , "yyyy-MM-dd");
		System.out.println(date);
	}
	
	@Test
	public void testLocalDateTimeFormat(){
		LocalDateTime localDateTime = LocalDateTime.now();
		String date = DateConverter.format(localDateTime , "yyyy-MM-dd HH:mm:ss");
		System.out.println(date);
	}
}
