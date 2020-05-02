package com.mibrahim.patientintake;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * BMI = (weight in pounds * 703) / height in inches squared (power by 2)
 */
public class BMICalculator {

	public static double calculateBmi(Integer inches, Integer pounds) {
		Double bmi = (double) (pounds * 703) / (Math.pow(inches, 2));
		double roundedToTwoPlaces = new BigDecimal(bmi).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return roundedToTwoPlaces;
	}

}
