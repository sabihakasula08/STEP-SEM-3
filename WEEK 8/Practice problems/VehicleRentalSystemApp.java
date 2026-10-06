// Abstract base class representing a Vehicle
abstract class RentalVehicle {
    private String model;
    private boolean isAvailable;

    public RentalVehicle(String model) {
        this.model = model;
        this.isAvailable = true;
    }

    public String getModel() {
        return this.model;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Abstract method allowing each category to define pricing rules[cite: 66]
    public abstract double calculateRentalCharge(int days);
}

class Sedan extends RentalVehicle {
    public Sedan(String model) {
        super(model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 50.0; // $50/day base rate
    }
}

class SUV extends RentalVehicle {
    public SUV(String model) {
        super(model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 80.0; // $80/day base rate
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

class RentalService {
    // Manages renting workflow and prevents duplicate active rentals[cite: 66]
    public void rentVehicle(Customer customer, RentalVehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getModel() + " is currently unavailable.");
            return;
        }

        vehicle.setAvailable(false);
        double charge = vehicle.calculateRentalCharge(days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.1f.\n",
                vehicle.getModel(), customer.getName(), charge);
    }

    public void returnVehicle(Customer customer, RentalVehicle vehicle) {
        vehicle.setAvailable(true);
        System.out.println(vehicle.getModel() + " returned by " + customer.getName() + ".");
    }
}

public class VehicleRentalSystemApp {
    public static void main(String[] args) {
        RentalService service = new RentalService();

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        RentalVehicle sedanA = new Sedan("Sedan A");
        RentalVehicle suvB = new SUV("SUV B");

        // Customer 1 rents Sedan A for 3 days[cite: 66]
        service.rentVehicle(c1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A while unavailable[cite: 66]
        service.rentVehicle(c2, sedanA, 2);

        // Customer 1 returns Sedan A[cite: 66]
        service.returnVehicle(c1, sedanA);

        // Customer 3 rents SUV B for 5 days[cite: 66]
        service.rentVehicle(c3, suvB, 5);
    }
}