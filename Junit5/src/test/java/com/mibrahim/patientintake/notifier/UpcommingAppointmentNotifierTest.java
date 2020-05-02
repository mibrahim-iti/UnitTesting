package com.mibrahim.patientintake.notifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mibrahim.patientintake.ClinicCalendar;
import com.mibrahim.patientintake.Doctor;

public class UpcommingAppointmentNotifierTest {

	private EmailNotifierTestDouble emailDouble;

	@BeforeEach
	void init() {
		emailDouble = new EmailNotifierTestDouble();
	}

	@Test
	void sendNotificationWithCorrectFormat() {
		LocalDate today = LocalDate.now();
		ClinicCalendar calendar = new ClinicCalendar(today);
		calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", LocalDateTime
				.of(today.plusDays(1), LocalTime.of(13, 30)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));
		UpcommingAppointmentNotifier notifier = new UpcommingAppointmentNotifier(calendar, emailDouble);
		notifier.run();

		assertEquals(1, emailDouble.receivedMessages.size());
		EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
		assertAll(() -> assertEquals("mohamedibrahim@mail.com", expectedMessage.toAddress),
				() -> assertEquals("Appointment Reminder", expectedMessage.subject),
				() -> assertEquals(String.format("You have an appointment tomorrow at 01:30 PM with Dr. %s.",
						Doctor.youssuf.getName()), expectedMessage.body));
	}
}
