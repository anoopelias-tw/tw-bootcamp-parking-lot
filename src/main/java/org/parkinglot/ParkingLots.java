package org.parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ParkingLots extends ArrayList<ParkingLot> {

    public ParkingLot getAvailableParkingLot() throws AllParkingLotsAreFullException {
        for (ParkingLot parkingLot : this) {
            if (!parkingLot.isFull()) {
                return parkingLot;
            }
        }
        throw new AllParkingLotsAreFullException();
    }

}
