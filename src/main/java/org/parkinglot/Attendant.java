package org.parkinglot;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Attendant implements ParkingLotObserver {

    private final ParkingLots availableLots = new ParkingLots();
    private final Map<Parkable, ParkingLot> cars = new HashMap<>();

    public void assignLot(ParkingLot parkingLot) {
        parkingLot.addObserver(this);

        if (!parkingLot.isFull()) {
            availableLots.add(parkingLot);
        }
    }

    private ParkingLot selectParkingLot() throws AllParkingLotsAreFullException {
        Optional<ParkingLot> parkingLot = availableLots.stream().max(Comparator.comparing(ParkingLot::capacity));

        if (parkingLot.isEmpty()) {
            throw new AllParkingLotsAreFullException();
        }

        return parkingLot.get();
    }

    public void park(Parkable car) throws AlreadyParkedException, AllParkingLotsAreFullException {
        if (cars.containsKey(car)) {
            throw new AlreadyParkedException();
        }

        try {
            ParkingLot parkingLot = selectParkingLot();
            parkingLot.park(car);
            cars.put(car, parkingLot);
        } catch (ParkingLotFullException e) {
            System.out.println("Unreachable code");
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

    public void unpark(Parkable car) {
        try {
            cars.get(car).unpark(car);
            cars.remove(car);
        } catch (NotParkedHereException e) {
            System.out.println("Unreachable code");
        }
    }
}
