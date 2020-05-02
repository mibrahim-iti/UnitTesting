package com.mibrahim.patientintake;

public enum Doctor {

	mibrahim("Mohamed Ibrahim"), youssuf("Youssuf Mohamady"), mfarag("Mohamed Farag");

	private String name;

	Doctor(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
