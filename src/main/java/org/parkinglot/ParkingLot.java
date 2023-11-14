package org.parkinglot;

public class ParkingLot {
    private int noOfCars;
    private final int capacity;

    public ParkingLot(int capacity) {
        noOfCars = 0;
        this.capacity = capacity;
    }

    public void park(Parkable parkable) throws AlreadyParkedException, ParkingLotFullException {
        if (noOfCars == capacity) {
            throw new ParkingLotFullException();
        }
        parkable.park();
        noOfCars += 1;
    }
}
