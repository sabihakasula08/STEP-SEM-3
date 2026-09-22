// Abstract base class[cite: 16]
abstract class Drone {
    protected String id;

    public Drone(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id.trim();
    }

    public abstract String fly();
}

// Interface for trackable devices[cite: 16]
interface Trackable {
    String getLocation();
}

// Subclass extending Drone AND implementing Trackable[cite: 16]
class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Delivery drone " + this.id + " in flight";
    }

    @Override
    public String getLocation() {
        return this.id + " at Sector 4";
    }
}

// Subclass extending Drone WITHOUT implementing Trackable[cite: 16]
class ScoutDrone extends Drone {
    public ScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Scout drone " + this.id + " scouting area";
    }
}

// Independent class implementing Trackable without any Drone lineage[cite: 16]
class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty.");
        }
        this.id = id.trim();
    }

    @Override
    public String getLocation() {
        return this.id + " at Sector 4";
    }
}

public class SkylineDeliveryApp {

    // Safely type-checks and casts any Object to Trackable[cite: 16]
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }
}