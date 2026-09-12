class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            System.out.println("construction rejected"); //[cite: 8]
            this.seatsTotal = 0;
            this.seatsAvailable = 0;
            return;
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    // Decrements seats available; rejects when count would dip below 0[cite: 8]
    public void bookSeat() {
        if (this.seatsAvailable <= 0) {
            return; // silent rejection[cite: 8]
        }
        this.seatsAvailable--;
    }

    // Increments seats available; rejects when count would exceed total capacity[cite: 8]
    public void cancelBooking() {
        if (this.seatsAvailable >= this.seatsTotal) {
            return; // silent rejection[cite: 8]
        }
        this.seatsAvailable++;
    }

    public int getSeatsAvailable() {
        return this.seatsAvailable;
    }
}

public class SeatBookingGuardApp {
    public static void main(String[] args) {
        new CineScreen(0);

        CineScreen screen = new CineScreen(2);
        screen.bookSeat();
        screen.bookSeat();
        screen.bookSeat(); // 3rd booking rejected[cite: 8]
        System.out.println(screen.getSeatsAvailable()); // 0[cite: 8]

        screen.cancelBooking();
        screen.cancelBooking();
        screen.cancelBooking(); // 3rd cancel rejected[cite: 8]
        System.out.println(screen.getSeatsAvailable()); // 2[cite: 8]
    }
}