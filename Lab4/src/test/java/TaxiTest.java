import org.junit.jupiter.api.Test;
import people.Firefighter;
import people.Person;
import people.Policeman;
import vehicle.Taxi;
import vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaxiTest {

    @Test
    void testBus_ShouldAcceptAllPassengers() {
        Vehicle<Person> taxi = new Taxi(4);

        assertDoesNotThrow(() -> taxi.addPassenger(new Person("Person")));
        assertDoesNotThrow(() -> taxi.addPassenger(new Policeman("Policeman")));
        assertDoesNotThrow(() -> taxi.addPassenger(new Firefighter("FireFighter")));

        assertEquals(3, taxi.getOccupiedSeats(), "expected %d, got %d".formatted(3, taxi.getOccupiedSeats()));
    }
}
