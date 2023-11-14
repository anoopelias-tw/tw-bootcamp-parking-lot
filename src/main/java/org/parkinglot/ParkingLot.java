package org.parkinglot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {
    private final int capacity;

    private final Set<Parkable> cars;

    private final Set<ParkingLotObserver> observers;

    public ParkingLot(int capacity) {
        cars = new HashSet<>();
        observers = new HashSet<>();
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

        if (cars.size() == capacity) {
            this.notifyFull();
        }
    }

    private void notifyFull() {
        for (ParkingLotObserver observer : observers) {
            observer.notifyFull();
        }
    }

    public void unpark(Parkable car) throws NotParkedHereException {
        if (!cars.contains(car)) {
            throw new NotParkedHereException();
        }

        cars.remove(car);
    }

    public void addObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }
}
