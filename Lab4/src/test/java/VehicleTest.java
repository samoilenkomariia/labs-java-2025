import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import people.Person;
import vehicle.Automobile;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VehicleTest {

    private final List<Person> testData = new ArrayList<>(
            List.of(new Person("person1"),
                    new Person("person2"),
                    new Person("person3"),
                    new Person("person4"),
                    new Person("person5"))
    );

    private Vehicle<Person> testCar;

    @BeforeEach
    void setUp() {
        testCar = new Automobile<>(4);
    }

    @Test
    void testAddPassenger_Success() {
        for(int i = 0; i < 4; i++) {
            testCar.addPassenger(testData.get(i));
            assertEquals(i+1, testCar.getOccupiedSeats(), "expected %d, got %d".formatted(i+1, testCar.getOccupiedSeats()));
        }
    }

    @Test
    void testAddPassenger_WhenFull_ThrowException() {
        for(int i = 0; i < 4; i++) {
            testCar.addPassenger(testData.get(i));
        }
        assertThrows(IllegalStateException.class, () -> testCar.addPassenger(testData.get(testData.size()-1)));
    }

    @Test
    void testRemovePassenger_Success() {
        for(int i = 0; i < 2; i++) {
            testCar.addPassenger(testData.get(i));
        }
        testCar.removePassenger(testData.get(0));
        assertEquals(1, testCar.getOccupiedSeats(), "expected %d, got %d".formatted(1, testCar.getOccupiedSeats()));
        testCar.removePassenger(testData.get(1));
        assertEquals(0, testCar.getOccupiedSeats(), "expected %d, got %d".formatted(0, testCar.getOccupiedSeats()));
    }

    @Test
    void testRemovePassenger_NonExistent_ThrowException() {
        testCar.addPassenger(testData.get(0));
        assertThrows(IllegalArgumentException.class, () -> testCar.removePassenger(testData.get(4)));
    }
}
