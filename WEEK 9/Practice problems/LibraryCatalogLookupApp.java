import java.util.ArrayList;
import java.util.List;

public class LibraryCatalogLookupApp {

    public static class BookRecord {
        public final String isbn;
        public final String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    // Binary search over sorted ISBNs
    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        if (catalog == null || catalog.isEmpty() || targetIsbn == null) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookRecord midRecord = catalog.get(mid);
            int cmp = midRecord.isbn.compareTo(targetIsbn);

            if (cmp == 0) {
                return midRecord.title; // Target ISBN found
            } else if (cmp < 0) {
                low = mid + 1; // Search right half
            } else {
                high = mid - 1; // Search left half[cite: 49]
            }
        }

        return "Not Found"; //[cite: 49]
    }

    public static void main(String[] args) {
        List<BookRecord> catalog = new ArrayList<>();
        catalog.add(new BookRecord("0001112223", "Introduction to Algebra"));
        catalog.add(new BookRecord("0002223334", "Beginning Python"));
        catalog.add(new BookRecord("0003334445", "Classic Mythology"));
        catalog.add(new BookRecord("0004445556", "Data and Society"));
        catalog.add(new BookRecord("0005556667", "European History"));

        // Example 1[cite: 49]
        System.out.println("Output 1: " + findBook(catalog, "0003334445")); // "Classic Mythology"[cite: 49]

        // Example 2[cite: 49]
        System.out.println("Output 2: " + findBook(catalog, "0009998887")); // "Not Found"[cite: 49]
    }
}