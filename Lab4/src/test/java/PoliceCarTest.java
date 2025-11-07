import org.junit.jupiter.api.Test;
import people.Policeman;
import vehicle.PoliceCar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoliceCarTest {

    @Test
    void testPoliceCar_ShouldAcceptOnlyPolicemen() {
        PoliceCar car = new PoliceCar(4);
        assertDoesNotThrow(() -> car.addPassenger(new Policeman("Policeman")));
        assertEquals(1, car.getOccupiedSeats());;
    }
}
