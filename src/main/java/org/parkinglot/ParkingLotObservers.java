package org.parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLotObservers extends HashSet<ParkingLotObserver> {

    public void notifyFull() {
        for (ParkingLotObserver observer : this) {
            observer.notifyFull();
        }
    }
}
