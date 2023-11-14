package org.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    private static class MockParkable implements Parkable {
        private final boolean isParked;

        private MockParkable(boolean isParked) {
            this.isParked = isParked;
        }

        public void park() throws AlreadyParkedException {
            if (isParked) {
                throw new AlreadyParkedException();
            }
        }
    }

    @Test
    public void testParkCar() throws AlreadyParkedException, ParkingLotFullException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(car);
    }

    @Test
    public void testParkAlreadyParkedCar() {
        MockParkable car = new MockParkable(true);
        ParkingLot parkingLot = new ParkingLot(2);
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
