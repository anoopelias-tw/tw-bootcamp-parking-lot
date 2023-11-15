package org.parkinglot;

import java.util.Set;

public class Attendant {

    private final ParkingLots parkingLots = new ParkingLots();

    public void assignLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public void park(Parkable car) throws AlreadyParkedException, AllParkingLotsAreFullException {
        try {
            parkingLots.getAvailableParkingLot().park(car);
        } catch (ParkingLotFullException e) {
            // Unreachable code
        }

    }
}
