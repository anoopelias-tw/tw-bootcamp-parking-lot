package org.parkinglot;

public class ChooseFirstParkingLotSelector implements ParkingLotSelector {
    @Override
    public ParkingLot select(ParkingLots parkingLots) {
        return parkingLots.iterator().next();
    }
}
