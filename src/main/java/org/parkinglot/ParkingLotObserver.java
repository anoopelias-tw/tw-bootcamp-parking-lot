package org.parkinglot;

public interface ParkingLotObserver {
    public void notifyFull();

    public void notifyAvailable();
}
