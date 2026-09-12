class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    // Validates memberId is non-blank and at least 4 characters long[cite: 10]
    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("memberId must be at least 4 characters long.");
        }
        this.memberId = memberId.trim();
        this.monthlyFee = Math.max(0, monthlyFee);
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        this.sessionsAttended++;
    }

    public int getSessionsAttended() {
        return this.sessionsAttended;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    // Forwards shared parameters via super(...)[cite: 10]
    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return this.trainerName;
    }
}

public class GymTrialBatchApp {

    // Attempts construction per array entry and counts rejections via try-catch[cite: 10]
    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds == null) {
            return "Signed Up: 0 | Rejected: 0";
        }

        for (String id : memberIds) {
            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        // Test 1: Construction validation[cite: 10]
        try {
            new GymMember("GM1", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Test 2: Subclass session counter[cite: 10]
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println(p.getSessionsAttended()); // 2[cite: 10]

        // Test 3: Batch signup processing[cite: 10]
        String[] batch = {"MEM1", "GM1", "MEM2", "", "MEM3"};
        System.out.println(signUpBatch(batch, 1000)); // Signed Up: 3 | Rejected: 2[cite: 10]
    }
}