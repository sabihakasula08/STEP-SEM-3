class SettlementGymMember {
    private static int counter = 2000;
    private static int membersEnrolled = 0;

    public final String membershipNumber;
    protected int monthlyFee;
    protected int feesPaid;

    public SettlementGymMember(int monthlyFee) {
        counter++;
        this.membershipNumber = "GYM-" + counter;
        membersEnrolled++;
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    // Overloaded payFee reusing 1-parameter method[cite: 10]
    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return this.feesPaid;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    // Validates exact format: "G" + two digits + one uppercase letter without regex[cite: 10]
    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }
}

class SettlementGroupMember extends SettlementGymMember {
    private String className;

    public SettlementGroupMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

public class GymWeeklySettlementApp {

    // Safely handles batch settlement with null checks and instanceof separation[cite: 10]
    public static String processWeeklyCheckIn(SettlementGymMember[] members) {
        int processedCount = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        for (SettlementGymMember member : members) {
            if (member == null) {
                nullCount++;
                continue;
            }
            processedCount++;
            if (member instanceof SettlementGroupMember) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return String.format("%d processed | %d null skipped | %d group | %d individual",
                processedCount, nullCount, groupCount, individualCount);
    }

    public static void main(String[] args) {
        SettlementGymMember m1 = new SettlementGymMember(1000);
        System.out.println(m1.membershipNumber); // "GYM-2001"[cite: 10]
        System.out.println(SettlementGymMember.getMembersEnrolled()); // 1[cite: 10]

        System.out.println(SettlementGymMember.isValidReferralCode("G45B")); // true[cite: 10]
        System.out.println(SettlementGymMember.isValidReferralCode("G4B"));  // false[cite: 10]
        System.out.println(SettlementGymMember.isValidReferralCode("X45B")); // false[cite: 10]

        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println(m1.getFeesPaid()); // 1000[cite: 10]

        SettlementGymMember[] batch = {
            new SettlementGroupMember(1500, "Zumba"),
            null,
            new SettlementGymMember(1000)
        };
        System.out.println(processWeeklyCheckIn(batch)); // "2 processed | 1 null skipped | 1 group | 1 individual"[cite: 10]
    }
}