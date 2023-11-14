package org.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    public void testParkCar() throws AlreadyParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(car);
    }

    @Test
    public void testParkAlreadyParkedCar() throws AlreadyParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(car);
        assertThrows(AlreadyParkedException.class,() -> parkingLot.park(car));
    }
}
