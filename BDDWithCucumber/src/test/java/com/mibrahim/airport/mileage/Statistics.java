package com.mibrahim.airport.mileage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mibrahim.airport.Passenger;
import com.mibrahim.mileage.Mileage;
import com.mibrahim.mileage.StatisticsUtil;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Statistics {
	private Passenger ibrahim;
	private Passenger mohamed;
	private Mileage mileage;

	@Given("^there is a usual passenger$")
	public void thereIsAUsualPassenger() throws Throwable {
		ibrahim = new Passenger("Ibrahim Ali", false);
		mileage = new Mileage();
	}

	@And("^there is a VIP passenger$")
	public void thereIsAVIPPassenger() throws Throwable {
		mohamed = new Passenger("Mohamed Ibrahim", true);
	}

	@When("^the usual passenger travels distances (\\d+) and (\\d+) and (\\d+)$")
	public void theUsualPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance1, int distance2,
			int distance3) throws Throwable {
		mileage.addMileageToList(ibrahim, distance1);
		mileage.addMileageToList(ibrahim, distance2);
		mileage.addMileageToList(ibrahim, distance3);
	}

	@And("^the VIP passenger travels distances (\\d+) and (\\d+) and (\\d+)$")
	public void theVIPPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance4, int distance5,
			int distance6) throws Throwable {
		mileage.addMileageToList(mohamed, distance4);
		mileage.addMileageToList(mohamed, distance5);
		mileage.addMileageToList(mohamed, distance6);
	}

	@Then("^the total travel distance should be (\\d+)$")
	public void theTotalTravelDistanceShouldBeTotal_distance(int total_distance) throws Throwable {
		assertEquals(total_distance, StatisticsUtil.totalDistance(mileage));
	}

	@When("^the usual passenger travels distances (\\d+) and (\\d+) and (\\d+) and (\\d+)$")
	public void theUsualPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance1, int distance2,
			int distance3, int distance4) throws Throwable {
		mileage.addMileageToList(ibrahim, distance1);
		mileage.addMileageToList(ibrahim, distance2);
		mileage.addMileageToList(ibrahim, distance3);
		mileage.addMileageToList(ibrahim, distance4);
	}

	@And("^the VIP passenger travels distances (\\d+) and (\\d+)$")
	public void theVIPPassengerTravelsDistancesDistanceAndDistanceAndDistance(int distance5, int distance6)
			throws Throwable {
		mileage.addMileageToList(mohamed, distance5);
		mileage.addMileageToList(mohamed, distance6);
	}

	@Then("^the minimum travel distance should be (\\d+)$")
	public void theMinimumTravelDistanceShouldBeMinimum_distance(int minimum_distance) throws Throwable {
		assertEquals(minimum_distance, StatisticsUtil.minimumDistance(mileage));
	}

	@And("^the maximum travel distance should be (\\d+)$")
	public void theMaximumTravelDistanceShouldBeMaximum_distance(int maximum_distance) throws Throwable {
		assertEquals(maximum_distance, StatisticsUtil.maximumDistance(mileage));
	}
}
