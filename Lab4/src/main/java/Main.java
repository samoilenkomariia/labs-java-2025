import people.Firefighter;
import people.Person;
import people.Policeman;
import vehicle.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        PoliceCar car = new PoliceCar(4);
//        car.addPassenger(new Firefighter("Tom"));

        Road road = new Road();
        Vehicle<Person> bus = new Bus(50);
        bus.addPassenger(new Person("Alice"));
        bus.addPassenger(new Policeman("Bob"));
        bus.addPassenger(new Firefighter("Charlie"));

        Vehicle<Policeman> policeCar = new PoliceCar(2);
        policeCar.addPassenger(new Policeman("Dana"));
        policeCar.addPassenger(new Policeman("Evan"));

        Vehicle<Firefighter> fireTruck = new FireTruck(6);
        fireTruck.addPassenger(new Firefighter("Frank"));

        Vehicle<Person> taxi = new Taxi(4);
        Vehicle<Person> taxi2 = new Taxi(3);
        taxi2.addPassenger(new Person("John"));
        taxi2.addPassenger(new Policeman("Mary"));
        taxi2.addPassenger(new Firefighter("Jane"));

        road.addAllVehicles(List.of(bus, policeCar, fireTruck, taxi, taxi2));
        road.carsInRoad.forEach(vehicle -> {
            System.out.println(
                    vehicle.getClass().getSimpleName() + " - " + vehicle.getOccupiedSeats() + " passengers"
            );
        });
    }
}
