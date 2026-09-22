// Abstract base class[cite: 16]
abstract class ClassroomDevice {
    public abstract String operate();
}

// Interface demonstrating compile-time method overloading[cite: 16]
interface Chargeable {
    String charge();
    String charge(int minutes);
}

class Tablet extends ClassroomDevice implements Chargeable {
    private String assetTag;

    public Tablet(String assetTag) {
        if (assetTag == null || assetTag.trim().isEmpty()) {
            throw new IllegalArgumentException("Asset tag cannot be empty.");
        }
        this.assetTag = assetTag.trim();
    }

    @Override
    public String operate() {
        return "Tablet " + this.assetTag + " displaying lesson";
    }

    @Override
    public String charge() {
        return this.assetTag + " charging";
    }

    @Override
    public String charge(int minutes) {
        return this.assetTag + " charging for " + minutes + " minutes";
    }
}

public class DigitalClassroomApp {
    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}