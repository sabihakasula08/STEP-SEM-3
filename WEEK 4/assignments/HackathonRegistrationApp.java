class Participant {
    private String name;
    private String teamName;
    private boolean registered;

    // Sets all three fields directly with registered set to true[cite: 6]
    public Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        this.registered = true;
    }

    // Chains via this(...) for solo entries[cite: 6]
    public Participant(String name) {
        this(name, "Unassigned");
    }

    public void printStatus() {
        System.out.printf("%s | %s | Registered: %b\n", this.name, this.teamName, this.registered);
    }
}

public class HackathonRegistrationApp {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teamNames = {"ByteBusters", "", "CodeCrafters", ""};

        for (int i = 0; i < names.length; i++) {
            Participant participant;
            if (teamNames[i].isEmpty()) {
                participant = new Participant(names[i]);
            } else {
                participant = new Participant(names[i], teamNames[i]);
            }
            participant.printStatus();
        }
    }
}
