package com.mibrahim.airport;

public class PremiumFlight extends Flight {

	public PremiumFlight(String id) {
		super(id);
	}

	@Override
	public boolean addPassenger(Passenger passenger) {
		if (passenger.isVip())
			return passengersSet.add(passenger);
		return false;
	}

	@Override
	public boolean removePassenger(Passenger passenger) {
		return false;
	}

}
