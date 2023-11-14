package org.parkinglot;

public class Car implements Parkable {
    private boolean parked = false;

    public void park() throws AlreadyParkedException {
        if (parked) {
            throw new AlreadyParkedException();
        }
        parked = true;
    }
}
