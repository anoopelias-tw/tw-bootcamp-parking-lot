package org.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    public void testParkCar() throws AlreadyParkedException, ParkingLotFullException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(car);
    }

    @Test
    public void testParkAlreadyParkedCar() throws AlreadyParkedException, ParkingLotFullException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(car);
        assertThrows(AlreadyParkedException.class,() -> parkingLot.park(car));
    }

    @Test
    public void testParkInFullParkingLot() throws AlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Car silverStallion = new Car();
        Car jeepCompass = new Car();
        parkingLot.park(silverStallion);
        assertThrows(ParkingLotFullException.class,() -> parkingLot.park(jeepCompass));
    }
}
