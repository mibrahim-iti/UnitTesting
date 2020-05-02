package com.mibrahim.patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter {

	public static LocalDateTime convertToDateFromString(String dateTime, LocalDate today) {
		LocalDateTime localDateTime;
		try {
			if (dateTime.trim().toLowerCase().startsWith("today")) {
				String[] parts = dateTime.split(" ", 2);
				LocalTime time = LocalTime.parse(parts[1].toUpperCase(),
						DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH));
				localDateTime = LocalDateTime.of(today, time);
			} else {
				localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
						DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a", Locale.ENGLISH));
			}
		} catch (Throwable t) {// Very bad catch, don't do this in prodution code
			throw new RuntimeException(String.format(
					"Unable to create date time from: [%s], please enter with format [dd/MM/yyyy hh:mm a]",
					dateTime.toUpperCase()));
		}
		return localDateTime;
	}
}
