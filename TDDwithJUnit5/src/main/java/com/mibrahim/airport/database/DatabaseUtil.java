package com.mibrahim.airport.database;

import java.util.ArrayList;
import java.util.List;

import com.mibrahim.airport.BusinessFlight;
import com.mibrahim.airport.EconomyFlight;
import com.mibrahim.airport.Flight;
import com.mibrahim.airport.Passenger;
import com.mibrahim.airport.PremiumFlight;

public class DatabaseUtil {

	private DatabaseUtil() {

	}

	public static List<Flight> buildFlightsList(List<List<String>> queriedData) {
		List<Flight> flightsList = new ArrayList<>();
		for (List<String> row : queriedData) {
			Flight flight;
			if (row.get(1).equals("e")) {
				flight = new EconomyFlight(row.get(0));
			} else if (row.get(1).equals("b")) {
				flight = new BusinessFlight(row.get(0));
			} else {
				flight = new PremiumFlight(row.get(0));
			}
			flight.addPassenger(new Passenger(row.get(2), Boolean.valueOf(row.get(3))));
			flight.setDistance(Integer.valueOf(row.get(4)));
			flightsList.add(flight);
		}
		return flightsList;
	}
}
