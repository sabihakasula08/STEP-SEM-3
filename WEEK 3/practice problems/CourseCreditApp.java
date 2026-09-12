class Course {
    private String code;
    private String title;
    private int credits;
    private int labCredits;

    // Primary 4-parameter constructor
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    // Overloaded 3-parameter constructor for theory-only courses chaining via this(...)
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    // Returns combined total credits
    public int totalCredits() {
        return this.credits + this.labCredits;
    }

    public String getCode() {
        return this.code;
    }
}

public class CourseCreditApp {
    public static void main(String[] args) {
        Course theoryCourse = new Course("21CSC201J", "Data Structures", 4);
        Course labCourse = new Course("21CSC205L", "DSA Lab", 3, 1);

        System.out.println(theoryCourse.getCode() + " total credits: " + theoryCourse.totalCredits());
        System.out.println(labCourse.getCode() + " total credits: " + labCourse.totalCredits());
    }
}