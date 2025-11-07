import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import people.Firefighter;
import people.Person;
import people.Policeman;
import vehicle.*;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {

    private Road road;

    @BeforeEach
    void setUp() {
        road = new Road();
    }

    @Test
    void testGetNumberOfPassengers_OnEmptyRoad() {
        assertEquals(0, road.getNumberOfPassengers());
    }

    @Test
    void testAddVehicle_AndGetNumberOfPassengers_WithVariousVehicles() {
        // 3
        Vehicle<Person> bus = new Bus(50);
        bus.addPassenger(new Person("Alice"));
        bus.addPassenger(new Policeman("Bob"));
        bus.addPassenger(new Firefighter("Charlie"));
        // 2
        Vehicle<Policeman> policeCar = new PoliceCar(2);
        policeCar.addPassenger(new Policeman("Dana"));
        policeCar.addPassenger(new Policeman("Evan"));
        // 1
        Vehicle<Firefighter> fireTruck = new FireTruck(6);
        fireTruck.addPassenger(new Firefighter("Frank"));
        // 0
        Vehicle<Person> taxi = new Taxi(4);
        //3
        Vehicle<Person> taxi2 = new Taxi(3);
        taxi2.addPassenger(new Person("John"));
        taxi2.addPassenger(new Policeman("Mary"));
        taxi2.addPassenger(new Firefighter("Jane"));

        road.addAllVehicles(List.of(bus, policeCar, fireTruck, taxi, taxi2));

        assertEquals(9, road.getNumberOfPassengers());
    }

    @Test
    void testAddAllVehicles_WithEmptyCollection() {
        road.addVehicle(new Taxi(4));
        road.addAllVehicles(Collections.emptyList());

        assertEquals(0, road.getNumberOfPassengers());
    }
}