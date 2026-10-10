import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class ClubMember {
    private String rollNumber;
    private String name;

    public ClubMember(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    // Only rollNumber is considered for uniqueness[cite: 61]
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubMember that = (ClubMember) o;
        return Objects.equals(rollNumber, that.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

public class ClubRegistrationApp {
    public static void main(String[] args) {
        Set<ClubMember> members = new HashSet<>();

        String[] ops = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };

        boolean countPrinted = false;

        for (String op : ops) {
            String[] tokens = op.split(" ");
            String cmd = tokens[0];
            String roll = tokens[1];
            String name = tokens[2];

            if (cmd.equals("ADD")) {
                ClubMember m = new ClubMember(roll, name);
                if (members.add(m)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (cmd.equals("CONTAINS")) {
                if (!countPrinted) {
                    System.out.println("member count " + members.size());
                    countPrinted = true;
                }
                ClubMember target = new ClubMember(roll, name);
                System.out.println("contains: " + members.contains(target));
            }
        }
    }
}