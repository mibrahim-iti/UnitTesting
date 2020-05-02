package com.mibrahim.patientintake;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClinicMain {

	private static ClinicCalendar calendar;

	public static void main(String[] args) throws Throwable {
		calendar = new ClinicCalendar(LocalDate.now());

		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Patient Intake system.\n\n");
		String lastOption = "";

		while (!lastOption.equalsIgnoreCase("x"))
			lastOption = displayMenu(scanner);
		System.out.println("\nExisting System...\n");
	}

	private static String displayMenu(Scanner scanner) throws Throwable {
		System.out.println("Please select an option:");
		System.out.println("1. Enter a Patient Appointment.");
		System.out.println("2. View all Appointments.");
		System.out.println("3. View Today Appointments.");
		System.out.println("4. Enter Patient Height Weight.");
		System.out.println("x. Exit System.");
		System.out.println("Option: ");
		String option = scanner.next();
		switch (option) {
		case "1":
			performPatientEntry(scanner);
			return option;
		case "2":
			performAllAppointments();
			return option;
		case "3":
			performTodayAppointments();
			return option;
		case "4":
			performHeightWeight(scanner);
			return option;
		default:
			System.out.println("Invalid option, please re-enter.");
			return option;
		}
	}

	private static void performPatientEntry(Scanner scanner) {
		scanner.nextLine();
		System.out.println("\n\nPlease Enter Appointment Info:");
		System.out.println(" Patient First Name: ");
		String firstname = scanner.nextLine();
		System.out.println(" Patient Last Name: ");
		String lastname = scanner.nextLine();
		System.out.println(" Appointment Date (dd/MM/yyyy hh:mm a) Example: 01/06/2020 01:30 pm");
		String dateTime = scanner.nextLine();
		System.out.println(" Doctor: ");
		String doctor = scanner.nextLine();
		try {
			calendar.addAppointment(firstname, lastname, doctor, dateTime);
		} catch (Throwable t) {
			System.out.println(String.format("Error! %s", t.getMessage()));
			return;
		}
		System.out.println("Patient entered successfully.\n\n");
	}

	private static void performAllAppointments() throws Throwable {
		System.out.println("\n\nAll Appointments in System:");
		listAppointments(calendar.getAppointments());
		System.out.println("\nPress any key to continue...");
		System.in.read();
		System.out.println("\n\n");
	}

	private static void performTodayAppointments() throws IOException {
		System.out.println("\n\nAppointmnent for today:");
		listAppointments(calendar.getTodayAppointments());
		System.out.println("\nPress any key to continue...");
		System.in.read();
		System.out.println("\n\n");
	}

	private static void listAppointments(List<PatientAppointment> appointments) {
		calendar.getAppointments().forEach(appointment -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
			String appointmentTime = formatter.format(appointment.getAppointmentDateTime());
			System.out.println(
					String.format("%s: %s, %s\t\tDoctor: %s,", appointmentTime, appointment.getPatientFirstName(),
							appointment.getPatientLastName(), appointment.getDoctor().getName()));
		});
	}

	private static void performHeightWeight(Scanner scanner) {
		scanner.nextLine();
		System.out.println("\n\nEnter patient height and weight for today's appointment:");
		System.out.println(" Patient First Name: ");
		String firstname = scanner.nextLine();
		System.out.println(" Patient Last Name: ");
		String lastname = scanner.nextLine();
		PatientAppointment appointment = findPatientAppointment(firstname, lastname).orElse(null);
		if (appointment != null) {
			System.out.println("Height in Inches: ");
			Integer inches = scanner.nextInt();
			System.out.println("Weight in Pounds: ");
			Integer pounds = scanner.nextInt();
			double roundedToTwoPlaces = BMICalculator.calculateBmi(inches, pounds);
			appointment.setBmi(roundedToTwoPlaces);
			System.out.println(String.format("Set patient BMI to %d\n\n", roundedToTwoPlaces));
		} else
			System.out.println("Patient not found.\n\n");
	}

	private static Optional<PatientAppointment> findPatientAppointment(String firstname, String lastname) {
		return calendar.getAppointments().stream()
				.filter(appointment -> appointment.getPatientFirstName().equals(firstname)
						&& appointment.getPatientLastName().equals(lastname))
				.findFirst();
	}
}
