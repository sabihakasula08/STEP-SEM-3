import java.util.Arrays;

// Immutable base class declared final with defensive copying[cite: 8]
final class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        // Defensive copy on the way in[cite: 8]
        if (seatNumbers != null) {
            this.seatNumbers = Arrays.copyOf(seatNumbers, seatNumbers.length);
        } else {
            this.seatNumbers = new String[0];
        }
    }

    // Defensive copy on the way out[cite: 8]
    public String[] getSeatNumbers() {
        return Arrays.copyOf(this.seatNumbers, this.seatNumbers.length);
    }

    public String getBookingId() {
        return this.bookingId;
    }

    // "Wither" pattern: returns fresh immutable instance with change applied[cite: 8]
    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = Arrays.copyOf(this.seatNumbers, this.seatNumbers.length);
        if (index >= 0 && index < updatedSeats.length) {
            updatedSeats[index] = newSeat;
        }
        return new BookingReceipt(this.bookingId, updatedSeats);
    }
}

// Standalone class representing group variant for settlement dispatch[cite: 8]
class GroupBookingReceipt {
    private final BookingReceipt receipt;
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        this.receipt = new BookingReceipt(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public BookingReceipt getReceipt() {
        return this.receipt;
    }
}

public class BookingReceiptSettlementApp {
    // Null-safe settlement processor using instanceof dispatch[cite: 8]
    public static String processNightlySettlement(Object[] receipts) {
        int processedCount = 0;
        int nullCount = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 group | 0 individual";
        }

        for (Object item : receipts) {
            if (item == null) {
                nullCount++;
                continue; // null-safe skip[cite: 8]
            }

            processedCount++;
            if (item instanceof GroupBookingReceipt) {
                groupCount++;
            } else if (item instanceof BookingReceipt) {
                individualCount++;
            }
        }

        return String.format("%d processed | %d null skipped | %d group | %d individual",
                processedCount, nullCount, groupCount, individualCount);
    }

    public static void main(String[] args) {
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println(b.getSeatNumbers()[0]); // "A1" - immutable![cite: 8]

        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println(Arrays.toString(b.getSeatNumbers()));       // [A1, A2][cite: 8]
        System.out.println(Arrays.toString(updated.getSeatNumbers())); // [A1, A3][cite: 8]

        Object[] batch = {
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println(processNightlySettlement(batch));
    }
}