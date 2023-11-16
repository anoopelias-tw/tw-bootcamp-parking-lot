package org.parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private final int capacity;

    private final Set<Parkable> cars;

    private final ParkingLotObservers observers;

    public ParkingLot(int capacity) {
        cars = new HashSet<>();
        observers = new ParkingLotObservers();
        this.capacity = capacity;
    }

    public int capacity() {
        return capacity;
    }

    public void park(Parkable car) throws AlreadyParkedException, ParkingLotFullException {

        if (cars.contains(car)) {
            throw new AlreadyParkedException();
        }

        if (isFull()) {
            throw new ParkingLotFullException();
        }

        cars.add(car);

        if (isFull()) {
            this.observers.notifyFull(this);
        }
    }

    public void unpark(Parkable car) throws NotParkedHereException {
        if (!cars.contains(car)) {
            throw new NotParkedHereException();
        }

        cars.remove(car);

        if (cars.size() == capacity - 1) {
            this.observers.notifyAvailable(this);
        }
    }

    public boolean isFull() {
        return cars.size() == capacity;
    }

    public void addObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }
}
