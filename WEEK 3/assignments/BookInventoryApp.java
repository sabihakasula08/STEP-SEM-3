class BookInventory {
    private String title;
    private String author;
    private int copiesAvailable;

    // Constructor setting all three inventory fields
    public BookInventory(String title, String author, int copiesAvailable) {
        if (title == null || author == null) {
            throw new IllegalArgumentException("Title and Author cannot be null.");
        }
        this.title = title;
        this.author = author;
        this.copiesAvailable = Math.max(0, copiesAvailable);
    }

    // Formatted line display
    public void printEntry() {
        System.out.printf("%s by %s - %d copies available\n", this.title, this.author, this.copiesAvailable);
    }
}

public class BookInventoryApp {
    public static void main(String[] args) {
        // Array of 4 BookInventory objects replacing parallel arrays
        BookInventory[] inventory = new BookInventory[4];

        inventory[0] = new BookInventory("Clean Code", "Robert C. Martin", 3);
        inventory[1] = new BookInventory("Effective Java", "Joshua Bloch", 5);
        inventory[2] = new BookInventory("Refactoring", "Martin Fowler", 0);
        inventory[3] = new BookInventory("Design Patterns", "GoF", 2);

        for (BookInventory book : inventory) {
            book.printEntry();
        }
    }
}