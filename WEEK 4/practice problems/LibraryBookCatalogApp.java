class LibraryBook {
    private String title;
    private String isbn;
    private boolean catalogued;

    // Primary two-argument constructor setting all fields[cite: 5]
    public LibraryBook(String title, String isbn) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be null or empty.");
        }
        this.title = title.trim();
        // Fall back to PENDING if isbn is null or empty[cite: 5]
        this.isbn = (isbn == null || isbn.trim().isEmpty()) ? "PENDING" : isbn.trim();
        this.catalogued = true;
    }

    // Overloaded single-argument constructor chaining via this(...)[cite: 5]
    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    // Displays formatted status line[cite: 5]
    public void printStatus() {
        System.out.printf("%s | %s | Catalogued: %b\n", this.title, this.isbn, this.catalogued);
    }
}

public class LibraryBookCatalogApp {
    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        // Single pass batch processing[cite: 5]
        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }
            book.printStatus();
        }
    }
}