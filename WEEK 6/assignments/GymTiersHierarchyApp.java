class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void setSessionsAttended(int count) {
        this.sessionsAttended = count;
    }

    public int getSessionsAttended() {
        return this.sessionsAttended;
    }

    public String displayInfo() {
        return "Standard Member | Sessions: " + this.sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    protected String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + this.trainerName + " | Sessions: " + this.sessionsAttended;
    }
}

// Multilevel inheritance extending PremiumMember[cite: 10]
class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + this.trainerName + " | Locker: " + this.lockerNumber + " | Sessions: " + this.sessionsAttended;
    }
}

// Hierarchical inheritance directly extending GymMember[cite: 10]
class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + this.className + " | Sessions: " + this.sessionsAttended;
    }
}

public class GymTiersHierarchyApp {

    // Identifies inheritance tier strictly using instanceof[cite: 10]
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Single-level descendant";
        }
        return "Standard base tier";
    }

    // Pure polymorphic summation of attendance counts[cite: 10]
    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        if (members == null) return 0;
        for (GymMember member : members) {
            if (member != null) {
                total += member.getSessionsAttended();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        PremiumMember premium = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group = new GroupClassMember("MEM4", 1500, "Zumba");

        premium.setSessionsAttended(3);
        elite.setSessionsAttended(2);
        group.setSessionsAttended(4);

        System.out.println(classifyGeneration(elite)); // Multilevel descendant[cite: 10]
        System.out.println(classifyGeneration(group)); // Hierarchical sibling[cite: 10]

        GymMember[] list = {premium, elite, group};
        System.out.println(getTotalSessionsAttended(list)); // 9[cite: 10]
    }
}