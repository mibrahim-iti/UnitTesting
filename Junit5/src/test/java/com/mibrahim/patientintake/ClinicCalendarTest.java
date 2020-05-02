package com.mibrahim.patientintake;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * Notes:
 * Assert all is used to print all failed assertions once, instead of getting it one by one
 * To know its power, try to make many assertions failed but extract all assertions out of assertAll
 */
class ClinicCalendarTest {

	private ClinicCalendar calendar;

	@BeforeEach
	public void init() {
		calendar = new ClinicCalendar(LocalDate.of(2020, 6, 1));
	}

	@Test
	void addAppointment_successfully() {
		calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "01/06/2020 01:30 pm");

		List<PatientAppointment> appointments = calendar.getAppointments();
		assertNotNull(appointments);
		assertEquals(1, appointments.size());

		PatientAppointment enteredAppointment = appointments.get(0);

		assertAll(() -> assertEquals("Mohamed", enteredAppointment.getPatientFirstName()),
				() -> assertEquals("Ibrahim", enteredAppointment.getPatientLastName()),
				() -> assertSame(Doctor.youssuf, enteredAppointment.getDoctor()),
				() -> assertEquals("01/06/2020 01:30 PM", enteredAppointment.getAppointmentDateTime()
						.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a"))));

	}

	@Test
	void returnTrueForHasAppointmentsIfThereAreAppointments() {
		calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "01/06/2020 01:30 pm");
		assertTrue(calendar.hasAppointment(LocalDate.of(2020, 06, 01)));
	}

	@Test
	void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
		assertFalse(calendar.hasAppointment(LocalDate.of(2020, 06, 01)));
	}

	@Nested
	@DisplayName("Return appointments correctly")
	class AppointmentsForDay {

		@Test
		@DisplayName("for today")
		void returnCurrentDayAppointments() {
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "01/06/2020 01:30 pm");
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "01/06/2020 01:30 pm");
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "02/06/2020 01:30 pm");
			assertEquals(2, calendar.getTodayAppointments().size());
		}

		@Test
		@DisplayName("for tomorrow")
		void returnTomorrowAppointments() {
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "01/06/2020 01:30 pm");
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "01/06/2020 01:30 pm");
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "02/06/2020 01:30 pm");
			assertEquals(1, calendar.getTomorrowAppointments().size());
		}
	}

	@Nested
	@DisplayName("Return upcomming appointments")
	class UpcommingAppointments {

		@Test
		@DisplayName("as empty list when there are none")
		void whenThereAreNone() {
			assertEquals(0, calendar.getUpcommingAppointments().size());
		}

		@Test
		@DisplayName("correctly when there are some past and future")
		void whenThereAreSomePastAndFuture() {
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "06/06/2018 01:30 pm");
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "06/06/2019 01:30 pm");
			calendar.addAppointment("Mohamed", "Ibrahim", "youssuf", "06/06/2020 01:30 pm");
			assertEquals(1, calendar.getUpcommingAppointments().size());
		}
	}

}
