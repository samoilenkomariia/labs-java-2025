package vehicle;

import people.Person;

public class Taxi extends Automobile<Person> {

    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}
