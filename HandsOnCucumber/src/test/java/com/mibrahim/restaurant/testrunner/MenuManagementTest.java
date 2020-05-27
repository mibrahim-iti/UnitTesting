package com.mibrahim.restaurant.testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, glue = {
		"com.mibrahim.restaurant.stepdefenition" }, plugin = { "pretty", "html:build/cucumber-html-report/html",
				"json:build/cucumber-html-report/json/report.json",
				"junit:build/cucumber-html-report/junit/report.xml" }, tags = {
						"@ListofMaps" }, dryRun = false, monochrome = true)
public class MenuManagementTest {

}
