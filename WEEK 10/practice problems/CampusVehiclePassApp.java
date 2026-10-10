import java.util.HashMap;
import java.util.Map;

interface ChargeableBayEligible {
    String bookChargingBay();
}

abstract class CampusVehicle {
    protected String passNumber;
    protected String ownerName;

    public CampusVehicle(String passNumber, String ownerName) {
        this.passNumber = passNumber;
        this.ownerName = ownerName;
    }

    public String getPassNumber() {
        return passNumber;
    }

    public abstract String getTypeName();
    public abstract int getPassFee();
}

class Bike extends CampusVehicle {
    public Bike(String passNumber, String ownerName) {
        super(passNumber, ownerName);
    }
    @Override public String getTypeName() { return "Bike"; }
    @Override public int getPassFee() { return 300; }
}

class Car extends CampusVehicle {
    public Car(String passNumber, String ownerName) {
        super(passNumber, ownerName);
    }
    @Override public String getTypeName() { return "Car"; }
    @Override public int getPassFee() { return 1000; }
}

class EBike extends CampusVehicle implements ChargeableBayEligible {
    public EBike(String passNumber, String ownerName) {
        super(passNumber, ownerName);
    }
    @Override public String getTypeName() { return "EBike"; }
    @Override public int getPassFee() { return 300; }
    @Override public String bookChargingBay() {
        return passNumber + " charging bay allotted";
    }
}

class ECar extends CampusVehicle implements ChargeableBayEligible {
    public ECar(String passNumber, String ownerName) {
        super(passNumber, ownerName);
    }
    @Override public String getTypeName() { return "ECar"; }
    @Override public int getPassFee() { return 1000; }
    @Override public String bookChargingBay() {
        return passNumber + " charging bay allotted";
    }
}

public class CampusVehiclePassApp {
    public static void main(String[] args) {
        Map<String, CampusVehicle> registry = new HashMap<>();

        String[] ops = {
            "PASS Bike KA01 Asha",
            "PASS ECar KA02 Ravi",
            "CHARGE KA02",
            "CHARGE KA01"
        };

        for (String line : ops) {
            String[] tokens = line.split(" ");
            if (tokens[0].equals("PASS")) {
                String type = tokens[1];
                String passNo = tokens[2];
                String owner = tokens[3];
                CampusVehicle v = null;

                if (type.equalsIgnoreCase("Bike")) v = new Bike(passNo, owner);
                else if (type.equalsIgnoreCase("Car")) v = new Car(passNo, owner);
                else if (type.equalsIgnoreCase("EBike")) v = new EBike(passNo, owner);
                else if (type.equalsIgnoreCase("ECar")) v = new ECar(passNo, owner);

                if (v != null) {
                    registry.put(passNo, v);
                    System.out.printf("%s (%s) pass fee %d\n", v.getPassNumber(), v.getTypeName(), v.getPassFee());
                }
            } else if (tokens[0].equals("CHARGE")) {
                String passNo = tokens[1];
                CampusVehicle v = registry.get(passNo);
                if (v instanceof ChargeableBayEligible) {
                    System.out.println(((ChargeableBayEligible) v).bookChargingBay());
                } else {
                    System.out.printf("%s rejected: charging unsupported\n", passNo);
                }
            }
        }
    }
}