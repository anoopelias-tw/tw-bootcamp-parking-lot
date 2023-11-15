package org.parkinglot;

import java.util.HashSet;
import java.util.Set;

public class Attendant implements ParkingLotObserver {

    private final ParkingLots availableLots = new ParkingLots();
    private final Set<Parkable> cars = new HashSet<>();

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

        if (cars.contains(car)) {
            throw new AlreadyParkedException();
        }

        try {
            availableLots.iterator().next().park(car);
        } catch (ParkingLotFullException e) {
            // Unreachable code
        }

        cars.add(car);
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
