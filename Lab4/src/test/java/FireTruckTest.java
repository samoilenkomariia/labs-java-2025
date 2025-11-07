import org.junit.jupiter.api.Test;
import people.Firefighter;
import vehicle.FireTruck;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FireTruckTest {

    @Test
    void testFireTruck_ShouldAcceptOnlyFirefighters() {
        FireTruck truck = new FireTruck(4);
        assertDoesNotThrow(() -> truck.addPassenger(new Firefighter("Firefighter")));
        assertEquals(1, truck.getOccupiedSeats());;
    }
}
