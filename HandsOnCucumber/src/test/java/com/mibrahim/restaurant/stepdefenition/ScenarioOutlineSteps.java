package com.mibrahim.restaurant.stepdefenition;

import static org.junit.Assert.assertTrue;

import com.mibrahim.restaurant.BillCalculationHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ScenarioOutlineSteps {

	int initialBillAmount;
	double taxRate;

	@Given("I have a Customer")
	public void i_have_a_Customer() {

	}

	@Given("user enters intial bill amount as {int}")
	public void user_enters_intial_bill_amount_as(Integer initialBillAmount) {
		this.initialBillAmount = initialBillAmount;
		System.out.println("InitialBillAmount: " + initialBillAmount);
	}

	@Given("Sales Tax Rate is {double} Percent")
	public void sales_Tax_Rate_is_Percent(Double taxRate) {
		this.taxRate = taxRate;
		System.out.println("Tax Rate: " + taxRate);
	}

	@Then("final bill amount calculated is {double}")
	public void final_bill_amount_calculate_is(Double expectedValue) {
		double systemCalcValue = BillCalculationHelper.CalculateBillForCustomer(this.initialBillAmount, this.taxRate);
		System.out.println("Expected Value: " + expectedValue);
		System.out.println("Calculated Value: " + systemCalcValue);
		assertTrue(expectedValue == systemCalcValue);
	}

}
