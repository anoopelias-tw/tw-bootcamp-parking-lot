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
    public void testAttendantToSelectSlotGivenByTheSelector() throws AlreadyParkedException, AllParkingLotsAreFullException, ParkingLotFullException {
        ParkingLotSelector selector = mock(ParkingLotSelector.class);
        Attendant attendant = new Attendant(selector);

        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);

        attendant.assignLot(parkingLot1);
        attendant.assignLot(parkingLot2);

        when(selector.select(any())).thenReturn(parkingLot1);
        attendant.park(mock(Parkable.class));

        verify(parkingLot1, times(1)).park(any());
        verify(parkingLot2, never()).park(any());
        verify(selector, times(1)).select(any());
    }
}
