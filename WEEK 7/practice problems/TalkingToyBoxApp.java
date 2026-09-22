// Abstract base class with shared static auto-incrementing ID[cite: 19]
abstract class Toy {
    private static int counter = 1000;
    private final String toyId;
    protected String name;

    public Toy(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Toy name cannot be blank.");
        }
        counter++;
        this.toyId = "TOY-" + counter;
        this.name = name.trim();
    }

    public String getToyId() {
        return this.toyId;
    }

    // Abstract method forcing distinct sounds per subclass[cite: 19]
    public abstract String makeSound();
}

class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return this.name + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return this.name + ": Beep boop!";
    }
}

public class TalkingToyBoxApp {
    public static void main(String[] args) {
        ToyCar c = new ToyCar("Speedster");
        System.out.println(c.makeSound()); // Speedster: Vroom vroom![cite: 19]

        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(r.makeSound()); // Bolt: Beep boop![cite: 19]

        System.out.println(c.getToyId());  // TOY-1001[cite: 19]
        System.out.println(r.getToyId());  // TOY-1002[cite: 19]
    }
}