package org.parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int capacity;

    private final Set<Parkable> cars;

    public ParkingLot(int capacity) {
        cars = new HashSet<>();
        this.capacity = capacity;
    }

    public void park(Parkable car) throws AlreadyParkedException, ParkingLotFullException {

        if (cars.contains(car)) {
            throw new AlreadyParkedException();
        }

        if (cars.size() == capacity) {
            throw new ParkingLotFullException();
        }
        cars.add(car);
    }

    public void unpark(Parkable car) throws NotParkedHereException {
        if (!cars.contains(car)) {
            throw new NotParkedHereException();
        }

        cars.remove(car);
    }
}
