import java.util.Arrays;

class FineLedgerMember {
    protected String memberId;
    protected int borrowLimit;
    private int[] fineHistory;
    private int fineCount;
    private int totalFine;

    public FineLedgerMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.fineHistory = new int[10];
        this.fineCount = 0;
        this.totalFine = 0;
    }

    // Base fine application and recording[cite: 9]
    protected void chargeFine(int amount) {
        if (amount > 0 && fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
            totalFine += amount;
        }
    }

    // Defensive copy preventing external array tampering[cite: 9]
    public int[] getFineHistory() {
        return Arrays.copyOf(this.fineHistory, this.fineCount);
    }

    public int getTotalFine() {
        return this.totalFine;
    }
}

class DiscountedStudentMember extends FineLedgerMember {
    private String course;

    public DiscountedStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    // Overrides fine charge to apply 50% discount via super[cite: 9]
    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class StudentFineLedgerApp {
    public static void main(String[] args) {
        DiscountedStudentMember s = new DiscountedStudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println(s.getTotalFine()); // 50[cite: 9]

        int[] history = s.getFineHistory();
        System.out.println(Arrays.toString(history)); // [50][cite: 9]

        history[0] = 999; // Attempted tampering
        System.out.println(Arrays.toString(s.getFineHistory())); // [50] remains untouched[cite: 9]
    }
}