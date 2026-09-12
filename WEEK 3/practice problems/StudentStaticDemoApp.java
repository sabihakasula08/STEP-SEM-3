class Student {
    // Instance fields unique to every student instance
    private String name;
    private double attendance;

    // Static fields shared across all instances
    public static String collegeName = "SRM Institute of Science and Technology";
    public static int studentCount = 0;

    public Student(String name, double attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++; // Increment counter on every object creation
    }

    // Static method accessing only static members
    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class StudentStaticDemoApp {
    public static void main(String[] args) {
        Student studentOne = new Student("Aarav", 88.5);
        Student studentTwo = new Student("Diya", 92.0);

        // Invoked directly through the class name without referencing an instance variable
        Student.printCollegeInfo();
    }
}