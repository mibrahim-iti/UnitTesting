package com.mibrahim.patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClinicCalendar {
	private LocalDate today;
	private List<PatientAppointment> appointments;

	public ClinicCalendar(LocalDate today) {
		this.today = today;
		this.appointments = new ArrayList<>();
	}

	public void addAppointment(String patientFirstName, String patientLastName, String doctorKey, String dateTime) {
		Doctor doctor = Doctor.valueOf(doctorKey);
		LocalDateTime localDateTime = DateTimeConverter.convertToDateFromString(dateTime, today);
		PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName, localDateTime,
				doctor);
		appointments.add(appointment);
	}

	public void removeAppointment(LocalDateTime dateTime) {
		Optional<PatientAppointment> appointment = appointments.stream()
				.filter(appt -> appt.getAppointmentDateTime().equals(dateTime)).findFirst();
		if (appointment.isPresent())
			appointments.remove(appointment.get());
	}

	public boolean hasAppointment(LocalDate date) {
		return appointments.stream()
				.anyMatch(appointment -> appointment.getAppointmentDateTime().toLocalDate().equals(date));
	}

	public List<PatientAppointment> getAppointments() {
		return appointments;
	}

	public List<PatientAppointment> getTodayAppointments() {
		return getAppointmentsForDate(today);
	}

	public List<PatientAppointment> getTomorrowAppointments() {
		return getAppointmentsForDate(today.plusDays(1));
	}

	private List<PatientAppointment> getAppointmentsForDate(LocalDate date) {
		return appointments.stream()
				.filter(appointment -> appointment.getAppointmentDateTime().toLocalDate().equals(date))
				.collect(Collectors.toList());
	}

	public List<PatientAppointment> getUpcommingAppointments() {
		return appointments.stream()
				.filter(appointment -> appointment.getAppointmentDateTime().toLocalDate().isAfter(today))
				.collect(Collectors.toList());
	}

}
