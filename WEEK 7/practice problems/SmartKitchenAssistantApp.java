// Abstract base class with validated speed-level property[cite: 19]
abstract class KitchenTool {
    private int speedLevel = 1; // sensible default

    public int getSpeedLevel() {
        return this.speedLevel;
    }

    // Validates range between 1 and 5; rejects without mutating state[cite: 19]
    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        } else {
            System.out.println("rejected, speed level stays " + this.speedLevel);
        }
    }

    public abstract String prepare();
}

// Interface for cleanable appliances[cite: 19]
interface Washable {
    String clean();
}

// Extends KitchenTool and implements Washable[cite: 19]
class Blender extends KitchenTool implements Washable {
    public Blender() {
        super();
    }

    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class SmartKitchenAssistantApp {
    public static void main(String[] args) {
        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println(b.getSpeedLevel()); // 3[cite: 19]

        b.setSpeedLevel(9); // rejected, speed level stays 3[cite: 19]

        System.out.println(b.prepare()); // Blending at speed 3[cite: 19]
        System.out.println(b.clean());   // Blender rinsed and dried[cite: 19]
    }
}