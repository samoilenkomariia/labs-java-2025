package vehicle;

import people.Person;

public class Automobile<T extends Person> extends Vehicle<T> {

    public Automobile(int maxSeats) {
        super(maxSeats);
    }
}
