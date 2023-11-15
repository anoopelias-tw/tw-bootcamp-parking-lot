package org.parkinglot;

import java.util.Set;

public class Attendant implements ParkingLotObserver {

    private final ParkingLots availableLots = new ParkingLots();

    public void assignLot(ParkingLot parkingLot) {
        parkingLot.addObserver(this);

        if (!parkingLot.isFull()) {
            availableLots.add(parkingLot);
        }
    }

    public void park(Parkable car) throws AlreadyParkedException, AllParkingLotsAreFullException {
        if (availableLots.isEmpty()) {
            throw new AllParkingLotsAreFullException();
        }

        try {
            availableLots.iterator().next().park(car);
        } catch (ParkingLotFullException e) {
            // Unreachable code
        }
    }


    @Override
    public void notifyFull(ParkingLot parkingLot) {
        availableLots.remove(parkingLot);
    }

    @Override
    public void notifyAvailable(ParkingLot parkingLot) {
        availableLots.add(parkingLot);
    }
}
