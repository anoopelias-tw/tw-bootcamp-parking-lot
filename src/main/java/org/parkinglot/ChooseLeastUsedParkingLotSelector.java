package org.parkinglot;

import java.util.Comparator;

public class ChooseLeastUsedParkingLotSelector implements ParkingLotSelector {
    @Override
    public ParkingLot select(ParkingLots parkingLots) {
        return parkingLots.stream().min(Comparator.comparing(ParkingLot::noOfCars)).get();
    }
}
