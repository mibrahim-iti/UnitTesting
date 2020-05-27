package com.mibrahim.restaurant;

import java.text.DecimalFormat;

public class BillCalculationHelper {

	public static double CalculateBillForCustomer(double billAmount, double taxrate) {

		double finalBillAmount = billAmount * (1 + taxrate / 100);

		DecimalFormat df = new DecimalFormat("0.00");

		String result = df.format(finalBillAmount);

		return Double.parseDouble(result);

	}

}
