class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        this.copiesTotal = Math.max(0, copiesTotal);
        this.copiesAvailable = this.copiesTotal;
    }

    // Silently rejects checkout if no copies are available[cite: 7]
    public void checkOut() {
        if (this.copiesAvailable <= 0) {
            return; // reject without crashing[cite: 7]
        }
        this.copiesAvailable--;
    }

    // Silently rejects checkin if already at full capacity[cite: 7]
    public void checkIn() {
        if (this.copiesAvailable >= this.copiesTotal) {
            return; // reject without crashing[cite: 7]
        }
        this.copiesAvailable++;
    }

    public int getCopiesAvailable() {
        return this.copiesAvailable;
    }
}

public class BookCirculationGuardApp {
    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut(); // 4th attempt rejected[cite: 7]
        System.out.println(b.getCopiesAvailable()); // 0[cite: 7]

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn(); // 4th attempt rejected[cite: 7]
        System.out.println(b.getCopiesAvailable()); // 3[cite: 7]
    }
}