import people.Person;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Person>> carsInRoad = new ArrayList<>();

    public void addVehicle(Vehicle<? extends Person> vehicle) {
        carsInRoad.add(vehicle);
    }

    public void addAllVehicles(Collection<Vehicle<? extends Person>> vehicles) {
        carsInRoad.addAll(vehicles);
    }

    public int getNumberOfPassengers() {
        if (carsInRoad.isEmpty()) {
            return 0;
        }
        int res = 0;
        for (var car : carsInRoad) {
            res += car.getOccupiedSeats();
        }
        return res;
    }
}
