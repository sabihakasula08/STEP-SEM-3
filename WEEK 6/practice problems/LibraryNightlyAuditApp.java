class AuditLibMember {
    private static int counter = 100;
    private static int membersEnrolled = 0;

    public final String memberNumber;
    protected int borrowLimit;
    protected int booksBorrowed;

    public AuditLibMember(int borrowLimit) {
        counter++;
        this.memberNumber = "LIB-" + counter;
        membersEnrolled++;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        this.booksBorrowed++;
    }

    // Overloaded borrowBook delegating internally to no-arg method[cite: 9]
    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }

    // Validates exact format: "R" + two digits + one uppercase letter without regex[cite: 9]
    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }
}

class AuditFacultyMember extends AuditLibMember {
    private String department;

    public AuditFacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class LibraryNightlyAuditApp {

    // Polymorphic batch audit with null check and instanceof separation[cite: 9]
    public static String processNightlyAudit(AuditLibMember[] members) {
        int processedCount = 0;
        int nullCount = 0;
        int facultyCount = 0;
        int regularCount = 0;

        if (members == null) {
            return "0 processed | 0 null skipped | 0 faculty | 0 regular";
        }

        for (AuditLibMember member : members) {
            if (member == null) {
                nullCount++;
                continue;
            }
            processedCount++;
            if (member instanceof AuditFacultyMember) {
                facultyCount++;
            } else {
                regularCount++;
            }
        }

        return String.format("%d processed | %d null skipped | %d faculty | %d regular",
                processedCount, nullCount, facultyCount, regularCount);
    }

    public static void main(String[] args) {
        AuditLibMember m1 = new AuditLibMember(3);
        System.out.println(m1.memberNumber); // "LIB-101"[cite: 9]
        System.out.println(AuditLibMember.getMembersEnrolled()); // 1[cite: 9]

        System.out.println(AuditLibMember.isValidRenewalCode("R12A")); // true[cite: 9]
        System.out.println(AuditLibMember.isValidRenewalCode("R1A"));  // false[cite: 9]
        System.out.println(AuditLibMember.isValidRenewalCode("X12A")); // false[cite: 9]

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed()); // 2[cite: 9]

        AuditLibMember[] batch = {
            new AuditFacultyMember(5, "Physics"),
            null,
            new AuditLibMember(3)
        };
        System.out.println(processNightlyAudit(batch));
    }
}