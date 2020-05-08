package com.mibrahim.airport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PassengersPolicy {

	private Flight economyFlight;
	private Flight businessFlight;
	private Flight premiumFlight;
	private Passenger ibrahim;
	private Passenger mohamed;

	@Given("^there is an economy flight$")
	public void thereIsAnEconomyFlight() throws Throwable {
		economyFlight = new EconomyFlight("1");
	}

	@When("^we have a usual passenger$")
	public void weHaveAUsualPassenger() throws Throwable {
		ibrahim = new Passenger("Ibrahim Ali", false);
	}

	@Then("^you can add and remove him from an economy flight$")
	public void youCanAddAndRemoveHimFromAnEconomyFlight() throws Throwable {
		assertAll("Verify all conditions for a usual passenger and an economy flight",
				() -> assertEquals("1", economyFlight.getId()),
				() -> assertEquals(true, economyFlight.addPassenger(ibrahim)),
				() -> assertEquals(1, economyFlight.getPassengersSet().size()),
				() -> assertTrue(economyFlight.getPassengersSet().contains(ibrahim)),
				() -> assertEquals(true, economyFlight.removePassenger(ibrahim)),
				() -> assertEquals(0, economyFlight.getPassengersSet().size()));
	}

	@When("^we have a VIP passenger$")
	public void weHaveAVIPPassenger() throws Throwable {
		mohamed = new Passenger("Mohamed Ibrahim", true);
	}

	@Then("^you can add him but cannot remove him from an economy flight$")
	public void youCanAddHimButCannotRemoveHimFromAnEconomyFlight() throws Throwable {
		assertAll("Verify all conditions for a VIP passenger and an economy flight",
				() -> assertEquals("1", economyFlight.getId()),
				() -> assertEquals(true, economyFlight.addPassenger(mohamed)),
				() -> assertEquals(1, economyFlight.getPassengersSet().size()),
				() -> assertTrue(economyFlight.getPassengersSet().contains(mohamed)),
				() -> assertEquals(false, economyFlight.removePassenger(mohamed)),
				() -> assertEquals(1, economyFlight.getPassengersSet().size()));
	}

	@Given("^there is an business flight$")
	public void thereIsAnBusinessFlight() throws Throwable {
		businessFlight = new BusinessFlight("2");
	}

	@Then("^you cannot add or remove him from a business flight$")
	public void youCannotAddOrRemoveHimFromABusinessFlight() throws Throwable {
		assertAll("Verify all conditions for a usual passenger and a business flight",
				() -> assertEquals(false, businessFlight.addPassenger(ibrahim)),
				() -> assertEquals(0, businessFlight.getPassengersSet().size()),
				() -> assertEquals(false, businessFlight.removePassenger(ibrahim)),
				() -> assertEquals(0, businessFlight.getPassengersSet().size()));
	}

	@Then("^you can add him but cannot remove him from a business flight$")
	public void youCanAddHimButCannotRemoveHimFromABusinessFlight() throws Throwable {
		assertAll("Verify all conditions for a VIP passenger and a business flight",
				() -> assertEquals(true, businessFlight.addPassenger(mohamed)),
				() -> assertEquals(1, businessFlight.getPassengersSet().size()),
				() -> assertEquals(false, businessFlight.removePassenger(mohamed)),
				() -> assertEquals(1, businessFlight.getPassengersSet().size()));
	}

	@Given("^there is an premium flight$")
	public void thereIsAnPremiumFlight() throws Throwable {
		premiumFlight = new PremiumFlight("3");
	}

	@Then("^you cannot add or remove him from a premium flight$")
	public void youCannotAddOrRemoveHimFromAPremiumFlight() throws Throwable {
		assertAll("Verify all conditions for a usual passenger and a premium flight",
				() -> assertEquals(false, premiumFlight.addPassenger(ibrahim)),
				() -> assertEquals(0, premiumFlight.getPassengersSet().size()),
				() -> assertEquals(false, premiumFlight.removePassenger(ibrahim)),
				() -> assertEquals(0, premiumFlight.getPassengersSet().size()));
	}

	@Then("^you can add and remove him from a premium flight$")
	public void youCanAddAndRemoveHimFromAPremiumFlight() throws Throwable {
		assertAll("Verify all conditions for a VIP passenger and a premium flight",
				() -> assertEquals(true, premiumFlight.addPassenger(mohamed)),
				() -> assertEquals(1, premiumFlight.getPassengersSet().size()),
				() -> assertEquals(true, premiumFlight.removePassenger(mohamed)),
				() -> assertEquals(0, premiumFlight.getPassengersSet().size()));
	}

	@And("^you cannot add a usual passenger to an economy flight more than once$")
	public void youCannotAddAUsualPassengerToAnEconomyFlightMoreThanOnce() throws Throwable {
		for (int i = 0; i < 10; i++) {
			economyFlight.addPassenger(ibrahim);
		}
		assertAll("Verify a usual passenger can be added to an economy flight only once",
				() -> assertEquals(1, economyFlight.getPassengersSet().size()),
				() -> assertTrue(economyFlight.getPassengersSet().contains(ibrahim)), () -> assertTrue(
						new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Ibrahim Ali")));
	}

	@And("^you cannot add a VIP passenger to an economy flight more than once$")
	public void youCannotAddAVIPPassengerToAnEconomyFlightMoreThanOnce() throws Throwable {
		for (int i = 0; i < 10; i++) {
			economyFlight.addPassenger(mohamed);
		}

		assertAll("Verify a VIP passenger can be added to an economy flight only once",
				() -> assertEquals(1, economyFlight.getPassengersSet().size()),
				() -> assertTrue(economyFlight.getPassengersSet().contains(mohamed)), () -> assertTrue(
						new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Mohamed Ibrahim")));
	}

	@And("^you cannot add a VIP passenger to a business flight more than once$")
	public void youCannotAddAVIPPassengerToABusinessFlightMoreThanOnce() throws Throwable {
		for (int i = 0; i < 10; i++) {
			businessFlight.addPassenger(mohamed);
		}

		assertAll("Verify a VIP passenger can be added to a business flight only once",
				() -> assertEquals(1, businessFlight.getPassengersSet().size()),
				() -> assertTrue(businessFlight.getPassengersSet().contains(mohamed)), () -> assertTrue(
						new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName().equals("Mohamed Ibrahim")));
	}

	@And("^you cannot add a VIP passenger to a premium flight more than once$")
	public void youCannotAddAVIPPassengerToAPremiumFlightMoreThanOnce() throws Throwable {
		for (int i = 0; i < 10; i++) {
			premiumFlight.addPassenger(mohamed);
		}

		assertAll("Verify a VIP passenger can be added to a premium flight only once",
				() -> assertEquals(1, premiumFlight.getPassengersSet().size()),
				() -> assertTrue(premiumFlight.getPassengersSet().contains(mohamed)), () -> assertTrue(
						new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("Mohamed Ibrahim")));
	}

}
