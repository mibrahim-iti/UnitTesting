package com.mibrahim.patientintake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("dateTime")
@DisplayName("DateTimeConverter Test")
class DateTimeConverterTest {

	@Nested
	@DisplayName("Convert string with 'today' keyword")
	class TodayTests {
		@Test
		@DisplayName("correctly.")
		void convertTodayStringCorrectlyCaseInsensitive() {
			LocalDate today = LocalDate.now();
			LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString("today 01:30 pm", today);
			assertEquals(localDateTime,
					LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 13, 30),
					"Failed to convert 'today' string to expected date time, today passed was: " + today);
		}

		@Test // Using lamda expression with failer make the message only processable in case
				// of failer
		@DisplayName("correctly regardless of case.")
		void convertTodayStringCorrectly() {
			LocalDate today = LocalDate.now();
			LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString("ToDaY 01:30 pm", today);
			assertEquals(localDateTime,
					LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 13, 30),
					() -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
		}
	}

	@Test
	@DisplayName("Convert expected date time patter in string correctly.")
	void convertCorrectPatternToDateTime() {
		LocalDate localDate = LocalDate.of(2020, 6, 1);
		LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString("01/06/2020 01:30 pm", localDate);
		assertEquals(localDateTime,
				LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 13, 30));
	}

	@Test
	@DisplayName("Throw exception if entered pattern of string incorrect.")
	void throwExceptionIfIncorrectPatternProvided() {
		LocalDate localDate = LocalDate.of(2020, 6, 1);
		Throwable error = assertThrows(RuntimeException.class,
				() -> DateTimeConverter.convertToDateFromString("01/06/2020 0130 pm", localDate));
		assertEquals(String.format(
				"Unable to create date time from: [01/06/2020 0130 PM], please enter with format [dd/MM/yyyy hh:mm a]"),
				error.getMessage());
	}

	@Test
	@Disabled
	@DisplayName("This test is disabled just for testing @Disabled annotation.")
	void disabledTest() {
		fail("Disabled test is not working fine :(");
	}

}
