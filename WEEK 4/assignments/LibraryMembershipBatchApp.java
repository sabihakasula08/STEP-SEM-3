class MembershipCard {
    public static String libraryName;
    public static String validUntil;

    private String studentName;

    // Static block executes once when the class is loaded[cite: 6]
    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    public MembershipCard(String studentName) {
        this.studentName = studentName;
        System.out.println("Membership card issued: " + this.studentName);
    }
}

public class LibraryMembershipBatchApp {
    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            new MembershipCard(name);
        }
    }
}