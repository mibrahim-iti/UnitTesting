package com.mibrahim.airport;

import java.util.Objects;

public class Passenger {

	private String name;
	private boolean vip;

	public Passenger(String name, boolean vip) {
		this.name = name;
		this.vip = vip;
	}

	public String getName() {
		return name;
	}

	public boolean isVip() {
		return vip;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null || getClass() != object.getClass())
			return false;
		Passenger passenger = (Passenger) object;
		return Objects.equals(name, passenger.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
