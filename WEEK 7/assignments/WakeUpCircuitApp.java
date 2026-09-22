// Common interface without any shared base class[cite: 16]
interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private String time;

    public AlarmClock(String time) {
        if (time == null || time.trim().isEmpty()) {
            throw new IllegalArgumentException("Time cannot be empty.");
        }
        this.time = time.trim();
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + this.time;
    }
}

class Doorbell implements Ringable {
    private String location;

    public Doorbell(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        this.location = location.trim();
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + this.location;
    }
}

public class WakeUpCircuitApp {

    // Loops through an array of Ringable references polymorphically[cite: 16]
    public static void ringAll(Ringable[] devices) {
        if (devices == null) return;
        for (Ringable device : devices) {
            if (device != null) {
                System.out.println(device.ring());
            }
        }
    }

    public static void main(String[] args) {
        AlarmClock a = new AlarmClock("7:00 AM");
        Doorbell d = new Doorbell("Front Door");

        System.out.println(a.ring());
        System.out.println(d.ring());

        ringAll(new Ringable[]{a, d});
    }
}