import java.util.Arrays;

// Immutability: final class, all final fields, defensive copying in and out[cite: 7]
class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        // Defensive copy on constructor input[cite: 7]
        this.bookIds = (bookIds != null) ? Arrays.copyOf(bookIds, bookIds.length) : new String[0];
    }

    // Defensive copy on getter output[cite: 7]
    public String[] getBookIds() {
        return Arrays.copyOf(this.bookIds, this.bookIds.length);
    }

    public String getMemberId() {
        return this.memberId;
    }

    // Wither pattern for immutable alteration[cite: 7]
    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updatedIds = Arrays.copyOf(this.bookIds, this.bookIds.length);
        if (index >= 0 && index < updatedIds.length) {
            updatedIds[index] = newId;
        }
        return new LoanReceipt(this.memberId, updatedIds);
    }
}

// Subclass variant for reference-only items[cite: 7]
class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }
}

class CirculationLedger {
    public static String branchCode;

    // Static block initializing one-time state[cite: 7]
    static {
        branchCode = "BR-042";
    }

    // Null-safe O(1) space batch processor using instanceof[cite: 7]
    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processedCount = 0;
        int nullCount = 0;
        int referenceOnlyCount = 0;
        int regularCount = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullCount++;
                continue;
            }

            processedCount++;
            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnlyCount++;
            } else {
                regularCount++;
            }
        }

        return String.format("%d processed | %d null skipped | %d reference-only | %d regular",
                processedCount, nullCount, referenceOnlyCount, regularCount);
    }
}

public class NightlyCirculationLedgerApp {
    public static void main(String[] args) {
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println(r.getBookIds()[0]); // "BK-100"[cite: 7]

        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println(Arrays.toString(r.getBookIds()));         // [BK-100, BK-101][cite: 7]
        System.out.println(Arrays.toString(corrected.getBookIds())); // [BK-100, BK-102][cite: 7]

        LoanReceipt[] batch = {
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println(CirculationLedger.processNightlyCirculation(batch));
    }
}