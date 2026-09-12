import java.util.Arrays;

class GymMember {
    protected String memberId;
    protected int monthlyFee;
    private int[] lateFeeHistory;
    private int lateFeeCount;
    private int totalLateFees;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.lateFeeHistory = new int[10];
        this.lateFeeCount = 0;
        this.totalLateFees = 0;
    }

    protected void chargeLateFee(int amount) {
        if (amount > 0 && lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
            totalLateFees += amount;
        }
    }

    // Defensive copy protecting internal ledger array[cite: 10]
    public int[] getLateFeeHistory() {
        return Arrays.copyOf(this.lateFeeHistory, this.lateFeeCount);
    }

    public int getTotalLateFees() {
        return this.totalLateFees;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    // Overrides fee charge to apply half discount via super[cite: 10]
    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class GymFeeLedgerApp {
    public static void main(String[] args) {
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println(p.getTotalLateFees()); // 100[cite: 10]

        int[] history = p.getLateFeeHistory();
        System.out.println(Arrays.toString(history)); // [100][cite: 10]

        history[0] = 999; // Tampering attempt
        System.out.println(Arrays.toString(p.getLateFeeHistory())); // [100][cite: 10]
    }
}