package com.mibrahim.patientintake.notifier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.mibrahim.patientintake.ClinicCalendar;
import com.mibrahim.patientintake.PatientAppointment;

public class UpcommingAppointmentNotifier {
	ClinicCalendar calendar;
	EmailNotifier notifier;

	public UpcommingAppointmentNotifier(ClinicCalendar calendar, EmailNotifier notifier) {
		this.calendar = calendar;
		this.notifier = notifier;
	}

	public void run() {
		calendar.getTomorrowAppointments().forEach(appointment -> {
			sendNotificationForAppointment(appointment);
		});
	}

	private void sendNotificationForAppointment(PatientAppointment appointment) {
		String email = String.format("%s%s@mail.com", appointment.getPatientFirstName().toLowerCase(),
				appointment.getPatientLastName().toLowerCase());
		String messageBody = buildMessageBody(appointment);
		System.out.println("Sending notification with body: " + messageBody);
		notifier.sendNotification("Appointment Reminder", messageBody, email);
	}

	private String buildMessageBody(PatientAppointment appointment) {
		return String.format("You have an appointment tomorrow at %s with Dr. %s.",
				appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)),
				appointment.getDoctor().getName());
	}
}
