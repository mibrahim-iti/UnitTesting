package com.mibrahim.restaurant.stepdefenition;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataTableSteps {

	@Given("I placed an order for the following list of items")
	public void i_placed_an_order_for_the_following_list_of_items(DataTable dataTable) {
		// List of strings @ListofStrings
		List<String> billData = dataTable.transpose().asList(String.class);
		billData.forEach(System.out::println);
	}

	@Given("I placed an order for the following list of list of items")
	public void i_placed_an_order_for_the_following_list_of_list_of_items(DataTable dataTable) {
		// List of list of strings @ListofListofStrings
		List<List<String>> billData = dataTable.asLists(String.class);
		billData.forEach(billItems -> billItems.forEach(System.out::println));
	}

	@Given("I placed an order for the following list of maps of items")
	public void i_placed_an_order_for_the_following_items(DataTable dataTable) {
		// List of maps @ListofMaps
		List<Map<String, String>> billData = dataTable.asMaps(String.class, String.class);
		billData.forEach(billItems -> billItems.forEach((key, value) -> {
			System.out.println("Key: " + key);
			System.out.println("Value: " + value);
		}));

	}

	@When("I generate the bill")
	public void i_generate_the_bill() {
		// Implement the test case here
	}

	@Then("a bill for ${int} should be generated")
	public void a_bill_for_$_should_be_generated(Integer int1) {
		// Implement the test case here
	}
}
