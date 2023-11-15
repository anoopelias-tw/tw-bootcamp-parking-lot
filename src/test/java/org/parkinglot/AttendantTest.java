package org.parkinglot;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AttendantTest {

    @Test
    public void testAttendantCanDirectTheCarToHisParkingLot() throws AlreadyParkedException, AllParkingLotsAreFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Attendant attendant = new Attendant();
        attendant.assignLot(parkingLot);
        attendant.park(Mockito.mock(Parkable.class));
        assertThrows(AllParkingLotsAreFullException.class, () -> attendant.park(Mockito.mock(Parkable.class)));
    }

    @Test
    public void testAttendantCanHandleMultipleParkingLots() throws AlreadyParkedException, AllParkingLotsAreFullException {
        Attendant attendant = new Attendant();
        attendant.assignLot(new ParkingLot(1));
        attendant.assignLot(new ParkingLot(1));

        attendant.park(Mockito.mock(Parkable.class));
        attendant.park(Mockito.mock(Parkable.class));

        assertThrows(AllParkingLotsAreFullException.class, () -> attendant.park(Mockito.mock(Parkable.class)));
    }

}
