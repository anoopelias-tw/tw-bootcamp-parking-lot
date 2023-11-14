package org.parkinglot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ParkingLotTest {

    @Test
    public void testParkCar() throws AlreadyParkedException, ParkingLotFullException {
        Parkable car = Mockito.mock(Parkable.class);
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(car);
    }

    @Test
    public void testParkAlreadyParkedCar() throws AlreadyParkedException {
        Parkable car = Mockito.mock(Parkable.class);
        doThrow(AlreadyParkedException.class).when(car).park();
        ParkingLot parkingLot = new ParkingLot(2);
        assertThrows(AlreadyParkedException.class,() -> parkingLot.park(car));
    }

    @Test
    public void testParkInFullParkingLot() throws AlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Parkable silverStallion = Mockito.mock(Parkable.class);
        Parkable jeepCompass = Mockito.mock(Parkable.class);
        parkingLot.park(silverStallion);
        assertThrows(ParkingLotFullException.class,() -> parkingLot.park(jeepCompass));
    }
}
