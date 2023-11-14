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
    public void testParkAlreadyParkedCar() throws AlreadyParkedException, ParkingLotFullException {
        Parkable car = Mockito.mock(Parkable.class);
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(car);
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

    @Test
    public void testUnparkCar() throws ParkingLotFullException, AlreadyParkedException, NotParkedHereException {
        Parkable car = Mockito.mock(Parkable.class);
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(car);
        parkingLot.unpark(car);

        Parkable nextCar = Mockito.mock(Parkable.class);
        parkingLot.park(nextCar);
    }

    @Test
    public void testUnparkACarThatIsNotParkedThere() {
        Parkable car = Mockito.mock(Parkable.class);
        ParkingLot parkingLot = new ParkingLot(1);
        assertThrows(NotParkedHereException.class, () -> parkingLot.unpark(car));
    }
}
