package com.mibrahim.airport.milage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.mibrahim.airport.Flight;
import com.mibrahim.airport.Passenger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MilageTest {

	private Milage milage;

	@BeforeAll
	void beforeAll() {
		milage = new Milage();
	}

	@Disabled
	@ParameterizedTest
	// Values in CSV format
	@ValueSource(strings = { "1; e; Mohamed; false; 250", "2; b; Ibrahim; true; 269", "3; e; Mohamed; true; 820",
			"4; p; Ibrahim; true; 315" })
	void checkGivenPoints(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
		for (Passenger passenger : flight.getPassengersSet()) {
			milage.addMilage(passenger, flight.getDistance());
		}
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/flights_information.csv")
	void checkGivenPointsWithCsvInput(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
		for (Passenger passenger : flight.getPassengersSet()) {
			milage.addMilage(passenger, flight.getDistance());
		}
	}

	@AfterAll
	void afterAll() {
		milage.calculateGivenPoints();
		assertEquals(53, milage.getPassengersPointsMap().get(new Passenger("Mohamed", false)).intValue());
		assertEquals(58, milage.getPassengersPointsMap().get(new Passenger("Ibrahim", true)).intValue());
		System.out.println(milage.getPassengersPointsMap());
	}
}
