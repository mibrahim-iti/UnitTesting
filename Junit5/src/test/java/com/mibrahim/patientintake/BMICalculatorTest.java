package com.mibrahim.patientintake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BMICalculator Test")
public class BMICalculatorTest {

	@Test
	@DisplayName("Calculate BMI to two places correctly via inches and pounds")
	void calculateBmiCorrectly() {
		assertEquals(19.2, BMICalculator.calculateBmi(69, 130));
		assertEquals(21.52, BMICalculator.calculateBmi(70, 150));
	}
}
