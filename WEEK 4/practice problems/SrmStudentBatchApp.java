class SrmStudent {
    private String name;

    // Shared static fields set exactly once[cite: 5]
    public static String collegeName;
    public static String academicYear;

    // Static initialization block runs once on class loading[cite: 5]
    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }
}

public class SrmStudentBatchApp {
    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        // Creating students in a loop without re-triggering the static block[cite: 5]
        for (String studentName : names) {
            new SrmStudent(studentName);
        }
    }
}