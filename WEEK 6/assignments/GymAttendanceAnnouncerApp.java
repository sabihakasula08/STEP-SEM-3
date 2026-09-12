class GymMember {
    protected String memberId;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.sessionsAttended = 0;
    }

    public String displayInfo() {
        return "Standard | Sessions: " + this.sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return this.trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + this.trainerName + " | Sessions: " + this.sessionsAttended;
    }
}

public class GymAttendanceAnnouncerApp {

    // Builds announcement using StringBuilder and guarded downcasts[cite: 10]
    public static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();

        for (GymMember member : members) {
            announcement.append(member.displayInfo());

            // Type check before executing downcast[cite: 10]
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                announcement.append(" [Trainer via downcast: ").append(premium.getTrainerName()).append("]");
            }
            announcement.append(" | ");
        }

        return announcement.toString();
    }

    public static void main(String[] args) {
        GymMember[] roster = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(roster));
    }
}