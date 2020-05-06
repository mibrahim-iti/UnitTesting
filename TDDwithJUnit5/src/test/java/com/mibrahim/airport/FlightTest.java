package com.mibrahim.airport;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

public class FlightTest {

	private Flight economyFlight;
	private Passenger ibrahim;
	private Passenger mohamed;

	@BeforeEach
	void init() {
		economyFlight = new EconomyFlight("1");
		ibrahim = new Passenger("Ibrahim Ali", false);
		mohamed = new Passenger("Mohamed Ibrahim", true);
	}

	@Nested
	@DisplayName("Given there is an economy flight")
	class EconomyFlightTest {
		@Nested
		@DisplayName("When we have a usual passenger")
		class UsualPassenger {

			@Test
			@DisplayName("Then you can add and remove him/her from an economy flight")
			public void testEconomyFlightUsualPassenger() {
				assertAll("Verify all conditions for a usual passenger and an economy flight",
						() -> assertEquals("1", economyFlight.getId()),
						() -> assertEquals(true, economyFlight.addPassenger(ibrahim)),
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> assertTrue(economyFlight.getPassengersSet().contains(ibrahim)),
						() -> assertEquals(true, economyFlight.removePassenger(ibrahim)),
						() -> assertEquals(0, economyFlight.getPassengersSet().size()));
			}

			@RepeatedTest(5)
			@DisplayName("Then you cannot add him/her to an economy flight more than once")
			public void testEconomyFlightUsualPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
				for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
					economyFlight.addPassenger(ibrahim);
				}
				assertAll("Verify a usual passenger can be added to an economy flight only once",
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> assertTrue(economyFlight.getPassengersSet().contains(ibrahim)),
						() -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName()
								.equals("Ibrahim Ali")));
			}

		}

		@Nested
		@DisplayName("When we have a VIP passenger")
		class VipPassenger {
			@Test
			@DisplayName("Then you can add him/her but cannot remove him/her from an economy flight")
			void testEconomyFlightVipPassenger() {
				assertAll("Verify all conditions for a VIP passenger and an economy flight",
						() -> assertEquals("1", economyFlight.getId()),
						() -> assertEquals(true, economyFlight.addPassenger(mohamed)),
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> assertTrue(economyFlight.getPassengersSet().contains(mohamed)),
						() -> assertEquals(false, economyFlight.removePassenger(mohamed)),
						() -> assertEquals(1, economyFlight.getPassengersSet().size()));
			}

			@RepeatedTest(5)
			@DisplayName("Then you can't add him to an economy flight more than once")
			public void testEconomyFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
				for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
					economyFlight.addPassenger(mohamed);
				}
				assertAll("Verify a VIP passenger can be added to an economy flight only once",
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> assertTrue(economyFlight.getPassengersSet().contains(mohamed)),
						() -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName()
								.equals("Mohamed Ibrahim")));
			}
		}

	}

	@Nested
	@DisplayName("Given there is a business flight")
	class BusinessFlightTest {
		private Flight businessFlight;

		@BeforeEach
		void init() {
			businessFlight = new BusinessFlight("2");
		}

		@Nested
		@DisplayName("When we have a usual passenger")
		class UsualPassenger {

			@Test
			@DisplayName("Then you can't add or remove him/her from a business flight")
			void testBusinessFlightUsualPassenger() {
				assertAll("Verify all conditions for a usual passenger and a business flight",
						() -> assertEquals(false, businessFlight.addPassenger(ibrahim)),
						() -> assertEquals(0, businessFlight.getPassengersSet().size()),
						() -> assertEquals(false, businessFlight.removePassenger(ibrahim)),
						() -> assertEquals(0, businessFlight.getPassengersSet().size()));

			}
		}

		@Nested
		@DisplayName("When we have a VIP passenger")
		class VipPassenger {

			@Test
			@DisplayName("Then you can add him/her but can't remove him/her from a business flight")
			void testBusinessFlightVipPassenger() {
				assertAll("Verify all conditions for a VIP passenger and a business flight",
						() -> assertEquals(true, businessFlight.addPassenger(mohamed)),
						() -> assertEquals(1, businessFlight.getPassengersSet().size()),
						() -> assertEquals(false, businessFlight.removePassenger(mohamed)),
						() -> assertEquals(1, businessFlight.getPassengersSet().size()));
			}

			@RepeatedTest(5)
			@DisplayName("Then you cannot add him/her to a business flight more than once")
			public void testBusinessFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
				for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
					businessFlight.addPassenger(mohamed);
				}
				assertAll("Verify a VIP passenger can be added to a business flight only once",
						() -> assertEquals(1, businessFlight.getPassengersSet().size()),
						() -> assertTrue(businessFlight.getPassengersSet().contains(mohamed)),
						() -> assertTrue(new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName()
								.equals("Mohamed Ibrahim")));
			}
		}
	}

	@Nested
	@DisplayName("Given there is a premium flight")
	class PremiumFlightTest {
		private Flight premiumFlight;

		@BeforeEach
		void init() {
			premiumFlight = new PremiumFlight("3");
		}

		@Nested
		@DisplayName("When we have a usual passenger")
		class UsualPassenger {

			@Test
			@DisplayName("Then you can't add or remove him/her from a premium flight")
			void testBusinessFlightUsualPassenger() {
				assertAll("Verify all conditions for a usual passenger and a premium flight",
						() -> assertEquals(false, premiumFlight.addPassenger(ibrahim)),
						() -> assertEquals(0, premiumFlight.getPassengersSet().size()),
						() -> assertEquals(false, premiumFlight.removePassenger(ibrahim)),
						() -> assertEquals(0, premiumFlight.getPassengersSet().size()));

			}
		}

		@Nested
		@DisplayName("When we have a VIP passenger")
		class VipPassenger {

			@Test
			@DisplayName("Then you can add him/her but can't remove him/her from a premium flight")
			void testBusinessFlightVipPassenger() {
				assertAll("Verify all conditions for a VIP passenger and a premium flight",
						() -> assertEquals(true, premiumFlight.addPassenger(mohamed)),
						() -> assertEquals(1, premiumFlight.getPassengersSet().size()),
						() -> assertEquals(false, premiumFlight.removePassenger(mohamed)),
						() -> assertEquals(1, premiumFlight.getPassengersSet().size()));
			}

			@RepeatedTest(5)
			@DisplayName("Then you cannot add him to a premium flight more than once")
			public void testPremiumFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
				for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
					premiumFlight.addPassenger(mohamed);
				}
				assertAll("Verify a VIP passenger can be added to a premium flight only once",
						() -> assertEquals(1, premiumFlight.getPassengersSet().size()),
						() -> assertTrue(premiumFlight.getPassengersSet().contains(mohamed)),
						() -> assertTrue(new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName()
								.equals("Mohamed Ibrahim")));
			}
		}
	}

}
