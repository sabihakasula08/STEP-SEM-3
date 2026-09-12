
class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    // Base constructor validating minimum length and non-blank rules[cite: 9]
    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid memberId: must be at least 4 characters.");
        }
        this.memberId = memberId.trim();
        this.borrowLimit = Math.max(1, borrowLimit);
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (this.booksBorrowed < this.borrowLimit) {
            this.booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    // Single inheritance forwarding common fields via super(...)[cite: 9]
    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return this.course;
    }
}

public class LibraryBatchEnrollmentApp {

    // Attempts construction per array entry and counts rejections via try-catch[cite: 9]
    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolledCount = 0;
        int rejectedCount = 0;

        if (memberIds == null) {
            return "Enrolled: 0 | Rejected: 0";
        }

        for (String id : memberIds) {
            try {
                new LibraryMember(id, borrowLimit);
                enrolledCount++;
            } catch (IllegalArgumentException e) {
                rejectedCount++;
            }
        }

        return "Enrolled: " + enrolledCount + " | Rejected: " + rejectedCount;
    }

    public static void main(String[] args) {
        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed()); // 2[cite: 9]

        String[] batch = {"STU1", "LB1", "STU2", "", "STU3"};
        System.out.println(enrollBatch(batch, 3)); // Enrolled: 3 | Rejected: 2[cite: 9]
    }
}