import org.junit.jupiter.api.Test;
import people.Firefighter;
import people.Person;
import people.Policeman;
import vehicle.Bus;
import vehicle.Vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusTest {

    @Test
    void testBus_ShouldAcceptAllPassengers() {
        Vehicle<Person> bus = new Bus(36);

        assertDoesNotThrow(() -> bus.addPassenger(new Person("Person")));
        assertDoesNotThrow(() -> bus.addPassenger(new Policeman("Policeman")));
        assertDoesNotThrow(() -> bus.addPassenger(new Firefighter("FireFighter")));

        assertEquals(3, bus.getOccupiedSeats(), "expected %d, got %d".formatted(3, bus.getOccupiedSeats()));
    }
}
