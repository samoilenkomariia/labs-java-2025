package vehicle;

import people.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle <T extends Person> {
    private List<T> passengers;
    private final int maxSeats;

    public Vehicle(int maxSeats) {
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(T passenger) {
        if (getOccupiedSeats() < maxSeats) {
            passengers.add(passenger);
        } else {
            throw new IllegalStateException("The vehicle is full, no more passengers can board");
        }
    }

    public void removePassenger(T passenger) {
        if (!passengers.contains(passenger)) {
            throw new IllegalArgumentException("The passenger is not in the vehicle");
        }
        passengers.remove(passenger);
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public int getMaxSeats() {
        return maxSeats;
    }
}

