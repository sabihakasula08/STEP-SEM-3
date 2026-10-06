import java.util.ArrayList;
import java.util.List;

abstract class HotelRoom {
    private String roomNumber;

    public HotelRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends HotelRoom {
    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 100.0;
    }
}

class DeluxeRoom extends HotelRoom {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 180.0;
    }
}

class RoomReservation {
    private String customerName;
    private HotelRoom room;
    private int startDay;
    private int endDay;
    private boolean active;

    public RoomReservation(String customerName, HotelRoom room, int startDay, int endDay) {
        this.customerName = customerName;
        this.room = room;
        this.startDay = startDay;
        this.endDay = endDay;
        this.active = true;
    }

    public boolean overlapsWith(int start, int end) {
        if (!active) return false;
        return (this.startDay <= end && this.endDay >= start);
    }

    public void cancel() {
        this.active = false;
    }

    public HotelRoom getRoom() { return room; }
    public String getCustomerName() { return customerName; }
    public int getStartDay() { return startDay; }
    public int getEndDay() { return endDay; }
    public boolean isActive() { return active; }
}

class HotelManager {
    private List<RoomReservation> reservations = new ArrayList<>();

    public boolean isAvailable(HotelRoom room, int start, int end) {
        for (RoomReservation r : reservations) {
            if (r.getRoom().getRoomNumber().equals(room.getRoomNumber()) && r.overlapsWith(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void reserve(String customerName, HotelRoom room, int start, int end, String dateLabel) {
        if (!isAvailable(room, start, end)) {
            System.out.printf("%s %s is not available from %s.\n",
                    room.getClass().getSimpleName(), room.getRoomNumber(), dateLabel);
            return;
        }

        RoomReservation res = new RoomReservation(customerName, room, start, end);
        reservations.add(res);
        int days = (end - start);
        double price = room.calculatePrice(days);

        System.out.printf("Reservation confirmed for %s, %s %s (%s). Price: $%.1f.\n",
                customerName, room.getClass().getSimpleName(), room.getRoomNumber(), dateLabel, price);
    }

    public void cancelReservation(String customerName, HotelRoom room, String dateLabel) {
        for (RoomReservation r : reservations) {
            if (r.getCustomerName().equals(customerName) &&
                r.getRoom().getRoomNumber().equals(room.getRoomNumber()) &&
                r.isActive()) {
                r.cancel();
                System.out.printf("Reservation for %s, %s %s (%s) cancelled successfully.\n",
                        customerName, room.getClass().getSimpleName(), room.getRoomNumber(), dateLabel);
                return;
            }
        }
    }
}

public class HotelBookingSystemApp {
    public static void main(String[] args) {
        HotelManager manager = new HotelManager();

        HotelRoom room101 = new StandardRoom("101");
        HotelRoom room201 = new DeluxeRoom("201");

        // Customer A checks availability Jan 1-5[cite: 68]
        if (manager.isAvailable(room101, 1, 5)) {
            System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");
        }

        // Customer A reserves Jan 1-5[cite: 68]
        manager.reserve("Customer A", room101, 1, 5, "Jan 1-5");

        // Customer B attempts overlapping Jan 3-7[cite: 68]
        manager.reserve("Customer B", room101, 3, 7, "Jan 3 to Jan 7");

        // Customer A cancels[cite: 68]
        manager.cancelReservation("Customer A", room101, "Jan 1-5");

        // Customer C reserves Deluxe Room 201[cite: 68]
        manager.reserve("Customer C", room201, 10, 12, "Feb 10-12");
    }
}