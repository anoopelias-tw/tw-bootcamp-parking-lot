package org.parkinglot;

import java.util.Comparator;

public class ChooseLargestParkingLotSelector implements ParkingLotSelector {
    @Override
    public ParkingLot select(ParkingLots parkingLots) {
        return parkingLots
                .stream()
                .max(Comparator.comparing(ParkingLot::capacity))
                .get();
    }
}
