package com.mibrahim.airport;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mibrahim.mileage.Mileage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BonusPolicy {
	private Passenger ibrahim;
	private Passenger mohamed;
	private Mileage mileage;

	@Given("^we have a usual passenger with a mileage$")
	public void weHaveAUsualPassengerWithAMileage() throws Throwable {
		ibrahim = new Passenger("Ibrahim Ali", false);
		mileage = new Mileage();
	}

	@When("^the usual passenger travels (\\d+) and (\\d+) and (\\d+)$")
	public void theUsualPassengerTravelsAndAnd(int mileage1, int mileage2, int mileage3) throws Throwable {
		mileage.addMileage(ibrahim, mileage1);
		mileage.addMileage(ibrahim, mileage2);
		mileage.addMileage(ibrahim, mileage3);
	}

	@Then("^the bonus points of the usual passenger should be (\\d+)$")
	public void theBonusPointsOfTheUsualPassengerShouldBe(int points) throws Throwable {
		mileage.calculateGivenPoints();
		assertEquals(points, mileage.getPassengersPointsMap().get(ibrahim).intValue());
	}

	@Given("^we have a VIP passenger with a mileage$")
	public void weHaveAVIPPassengerWithAMileage() throws Throwable {
		mohamed = new Passenger("Mohamed Ibrahim", true);
		mileage = new Mileage();
	}

	@When("^the VIP passenger travels (\\d+) and (\\d+) and (\\d+)$")
	public void theVIPPassengerTravelsAndAnd(int mileage1, int mileage2, int mileage3) throws Throwable {
		mileage.addMileage(mohamed, mileage1);
		mileage.addMileage(mohamed, mileage2);
		mileage.addMileage(mohamed, mileage3);
	}

	@Then("^the bonus points of the VIP passenger should be (\\d+)$")
	public void theBonusPointsOfTheVIPPassengerShouldBe(int points) throws Throwable {
		mileage.calculateGivenPoints();
		assertEquals(points, mileage.getPassengersPointsMap().get(mohamed).intValue());
	}
}
