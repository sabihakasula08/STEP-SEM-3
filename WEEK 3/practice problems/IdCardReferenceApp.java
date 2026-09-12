class IdCard {
    String name;
    int booksIssued;

    public IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class IdCardReferenceApp {
    public static void main(String[] args) {
        // Original object
        IdCard ravi = new IdCard("Ravi", 0);

        // Reference copy pointing to the exact same heap memory address
        IdCard duplicate = ravi;
        duplicate.booksIssued = 3;

        // Print value seen from the first variable and reference comparison
        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));

        // Third, distinct object with identical contents
        IdCard separate = new IdCard("Ravi", 3);
        System.out.println("separate == ravi: " + (separate == ravi));
    }
}