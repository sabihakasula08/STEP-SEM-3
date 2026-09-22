// Level 1: Abstract Base Class[cite: 19]
abstract class Instrument {
    public abstract String play();
}

// Level 2: Intermediate Concrete Subclass[cite: 19]
class StringInstrument extends Instrument {
    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return "Strumming the strings";
    }
}

// Level 3: Multilevel Subclass layering additional detail via super.play()[cite: 19]
class Violin extends StringInstrument {
    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class OrchestraWarmUpApp {
    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        System.out.println(s.play()); // Strumming the strings[cite: 19]

        Violin v = new Violin();
        System.out.println(v.play()); // Strumming the strings, with a bow drawn across four strings[cite: 19]
    }
}