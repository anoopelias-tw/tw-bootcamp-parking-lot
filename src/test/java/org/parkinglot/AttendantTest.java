package org.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AttendantTest {

    @Test
    public void testAttendantCanDirectTheCarToHisParkingLot() throws AlreadyParkedException, AllParkingLotsAreFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Attendant attendant = new Attendant();
        attendant.assignLot(parkingLot);
        attendant.park(mock(Parkable.class));
        assertThrows(AllParkingLotsAreFullException.class, () -> attendant.park(mock(Parkable.class)));
    }

    @Test
    public void testAttendantCanHandleMultipleParkingLots() throws AlreadyParkedException, AllParkingLotsAreFullException {
        Attendant attendant = new Attendant();
        attendant.assignLot(new ParkingLot(1));
        attendant.assignLot(new ParkingLot(1));

        attendant.park(mock(Parkable.class));
        attendant.park(mock(Parkable.class));

        assertThrows(AllParkingLotsAreFullException.class, () -> attendant.park(mock(Parkable.class)));
    }

    @Test
    public void testParkAlreadyParkedCarInAnotherLot() throws AlreadyParkedException, AllParkingLotsAreFullException {
        Attendant attendant = new Attendant();
        attendant.assignLot(new ParkingLot(1));
        attendant.assignLot(new ParkingLot(1));

        Parkable car = mock(Parkable.class);
        attendant.park(car);

        assertThrows(AlreadyParkedException.class, () -> attendant.park(car));
    }

    @Test
    public void testAttendantCanUnparkTheCar() throws AlreadyParkedException, AllParkingLotsAreFullException {
        Attendant attendant = new Attendant();
        attendant.assignLot(new ParkingLot(1));

        Parkable car = mock(Parkable.class);
        attendant.park(car);

        attendant.unpark(car);
        attendant.park(car);
    }

    @Test
    public void testAttendantToSelectLargestAvailableSlot() throws AlreadyParkedException, AllParkingLotsAreFullException {
        Attendant attendant = new Attendant();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot largerParkingLot = new ParkingLot(2);

        attendant.assignLot(parkingLot);
        attendant.assignLot(largerParkingLot);

        ParkingLotObserver parkingLotObserver = mock(ParkingLotObserver.class);
        largerParkingLot.addObserver(parkingLotObserver);

        ParkingLotObserver largeParkingLotObserver = mock(ParkingLotObserver.class);
        largerParkingLot.addObserver(largeParkingLotObserver);

        attendant.park(mock(Parkable.class));
        attendant.park(mock(Parkable.class));

        verify(parkingLotObserver, never()).notifyFull(parkingLot);
        verify(largeParkingLotObserver, times(1)).notifyFull(largerParkingLot);

    }
}
