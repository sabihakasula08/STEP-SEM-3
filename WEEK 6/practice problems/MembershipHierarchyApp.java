// Hierarchical Base Class
class BaseLibMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public BaseLibMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void setBooksBorrowed(int count) {
        this.booksBorrowed = count;
    }

    public int getBooksBorrowed() {
        return this.booksBorrowed;
    }

    public String displayInfo() {
        return "General Member | Books Borrowed: " + this.booksBorrowed;
    }
}

// Single Inheritance
class HierarchyStudentMember extends BaseLibMember {
    private String course;

    public HierarchyStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student Member | Course: " + this.course + " | Books Borrowed: " + this.booksBorrowed;
    }
}

// Multilevel Inheritance (3 levels deep)[cite: 9]
class HierarchyHonorsMember extends HierarchyStudentMember {
    private int bonusLimit;

    public HierarchyHonorsMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: ECE | Bonus Limit: " + this.bonusLimit + " | Books Borrowed: " + this.booksBorrowed;
    }
}

// Hierarchical Inheritance (independent sibling branch)[cite: 9]
class HierarchyFacultyMember extends BaseLibMember {
    private String department;

    public HierarchyFacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + this.department + " | Books Borrowed: " + this.booksBorrowed;
    }
}

public class MembershipHierarchyApp {

    // Identifies inheritance tier via instanceof checks[cite: 9]
    public static String classifyGeneration(BaseLibMember member) {
        if (member instanceof HierarchyHonorsMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof HierarchyFacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof HierarchyStudentMember) {
            return "Single-level descendant";
        }
        return "Root base class";
    }

    // Pure polymorphic sum using getBooksBorrowed()[cite: 9]
    public static int getTotalBooksBorrowed(BaseLibMember[] members) {
        int total = 0;
        if (members == null) return 0;
        for (BaseLibMember member : members) {
            if (member != null) {
                total += member.getBooksBorrowed();
            }
        }
        return total;
    }

    public static void main(String[] args) {
        HierarchyStudentMember student = new HierarchyStudentMember("STU2", 3, "CSE");
        HierarchyHonorsMember honors = new HierarchyHonorsMember("STU3", 3, "ECE", 2);
        HierarchyFacultyMember faculty = new HierarchyFacultyMember("STU4", 5, "Physics");

        student.setBooksBorrowed(2);
        honors.setBooksBorrowed(1);
        faculty.setBooksBorrowed(3);

        System.out.println(student.displayInfo());
        System.out.println(honors.displayInfo());
        System.out.println(faculty.displayInfo());

        System.out.println(classifyGeneration(honors));  // Multilevel descendant (3 generations deep)[cite: 9]
        System.out.println(classifyGeneration(faculty)); // Hierarchical sibling (independent branch)[cite: 9]

        BaseLibMember[] mix = {student, honors, faculty};
        System.out.println(getTotalBooksBorrowed(mix)); // 6[cite: 9]
    }
}