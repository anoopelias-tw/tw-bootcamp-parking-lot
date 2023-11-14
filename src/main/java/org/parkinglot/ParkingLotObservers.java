package org.parkinglot;

import java.util.HashSet;

public class ParkingLotObservers extends HashSet<ParkingLotObserver> {

    public void notifyFull() {
        for (ParkingLotObserver observer : this) {
            observer.notifyFull();
        }
    }

    public void notifyAvailable() {
        for (ParkingLotObserver observer : this) {
            observer.notifyAvailable();
        }
    }
}
