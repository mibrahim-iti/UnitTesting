package com.mibrahim.mileage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mibrahim.airport.Passenger;

public class Mileage {

    public static final int VIP_FACTOR = 10;
    public static final int USUAL_FACTOR = 20;

    private Map<Passenger, Integer> passengersMileageMap = new HashMap<>();
    private Map<Passenger, Integer> passengersPointsMap = new HashMap<>();

    public Map<Passenger, Integer> getPassengersPointsMap() {
        return Collections.unmodifiableMap(passengersPointsMap);
    }

    public void addMileage(Passenger passenger, int miles) {
        if (passengersMileageMap.containsKey(passenger)) {
            passengersMileageMap.put(passenger, passengersMileageMap.get(passenger) + miles);
        } else {
            passengersMileageMap.put(passenger, miles);
        }

    }

    public void calculateGivenPoints() {
        for (Passenger passenger : passengersMileageMap.keySet()) {
            if (passenger.isVip()) {
                passengersPointsMap.put(passenger, passengersMileageMap.get(passenger)/ VIP_FACTOR);
            } else {
                passengersPointsMap.put(passenger, passengersMileageMap.get(passenger)/ USUAL_FACTOR);
            }
        }
    }

}
