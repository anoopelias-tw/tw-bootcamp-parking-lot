package org.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParkingLotSelectorTest {

    private ParkingLot parkingLotOne;
    private ParkingLot parkingLotTwo;
    private ParkingLots parkingLots;

    @BeforeEach
    void setup() {
        parkingLotOne = mock(ParkingLot.class);
        parkingLotTwo = mock(ParkingLot.class);
        parkingLots = new ParkingLots();
        parkingLots.add(parkingLotOne);
        parkingLots.add(parkingLotTwo);
    }

    @Test
    public void testChooseFirstParkingLotSelector() {
        ParkingLotSelector parkingLotSelector = new ChooseFirstParkingLotSelector();
        assertEquals(parkingLotOne, parkingLotSelector.select(parkingLots));
    }

    @Test
    public void testChooseLargestParkingLotSelector() {
        ParkingLotSelector parkingLotSelector = new ChooseLargestParkingLotSelector();
        when(parkingLotOne.capacity()).thenReturn(1);
        when(parkingLotTwo.capacity()).thenReturn(2);

        assertEquals(parkingLotTwo, parkingLotSelector.select(parkingLots));
    }
}
