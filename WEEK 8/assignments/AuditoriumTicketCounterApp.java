import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

abstract class CinemaSeat {
    private final String seatId;

    public CinemaSeat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() { return seatId; }
    public abstract double getPrice();
}

class RegularSeat extends CinemaSeat {
    public RegularSeat(String seatId) { super(seatId); }
    @Override public double getPrice() { return 150.00; }
}

class PremiumSeat extends CinemaSeat {
    public PremiumSeat(String seatId) { super(seatId); }
    @Override public double getPrice() { return 250.00; }
}

class ReclinerSeat extends CinemaSeat {
    public ReclinerSeat(String seatId) { super(seatId); }
    @Override public double getPrice() { return 400.00; }
}

class MovieShow {
    private final String showTime;
    private final Set<String> bookedSeatIds;
    private boolean hasStarted;

    public MovieShow(String showTime) {
        this.showTime = showTime;
        this.bookedSeatIds = new HashSet<>();
        this.hasStarted = false;
    }

    public boolean isSeatBooked(String seatId) {
        return bookedSeatIds.contains(seatId);
    }

    public void bookSeat(String seatId) {
        bookedSeatIds.add(seatId);
    }

    public void releaseSeat(String seatId) {
        bookedSeatIds.remove(seatId);
    }

    public boolean hasStarted() { return hasStarted; }
}

class MovieBooking {
    private final String customerName;
    private final MovieShow show;
    private final List<CinemaSeat> seats;

    public MovieBooking(String customerName, MovieShow show, List<CinemaSeat> seats) {
        this.customerName = customerName;
        this.show = show;
        this.seats = seats;
    }

    public String getCustomerName() { return customerName; }
    public MovieShow getShow() { return show; }
    public List<CinemaSeat> getSeats() { return seats; }

    public double calculateTotal() {
        double total = 0;
        for (CinemaSeat s : seats) {
            total += s.getPrice();
        }
        return total;
    }
}

class TicketCounterService {
    public MovieBooking createBooking(String customerName, MovieShow show, List<CinemaSeat> requestedSeats) {
        if (requestedSeats == null || requestedSeats.isEmpty() || requestedSeats.size() > 6) {
            System.out.println("Booking rejected: Must select between 1 and 6 seats.");
            return null;
        }

        // Validate seat availability[cite: 71, 72]
        for (CinemaSeat s : requestedSeats) {
            if (show.isSeatBooked(s.getSeatId())) {
                System.out.printf("Seat %s is already booked for this show.\n", s.getSeatId());
                return null;
            }
        }

        List<String> ids = new ArrayList<>();
        for (CinemaSeat s : requestedSeats) {
            show.bookSeat(s.getSeatId());
            ids.add(s.getSeatId());
        }

        MovieBooking booking = new MovieBooking(customerName, show, requestedSeats);
        System.out.printf("Booking confirmed for %s: %s. Total: %.2f.\n",
                customerName, String.join(", ", ids), booking.calculateTotal());
        return booking;
    }

    public void cancelBooking(MovieBooking booking) {
        if (booking == null) return;
        if (booking.getShow().hasStarted()) {
            System.out.println("Cannot cancel booking: Show has already started.");
            return;
        }

        List<String> ids = new ArrayList<>();
        for (CinemaSeat s : booking.getSeats()) {
            booking.getShow().releaseSeat(s.getSeatId());
            ids.add(s.getSeatId());
        }

        System.out.printf("%s's booking cancelled. Seats %s released.\n",
                booking.getCustomerName(), String.join(", ", ids));
    }
}

public class AuditoriumTicketCounterApp {
    public static void main(String[] args) {
        TicketCounterService counter = new TicketCounterService();
        MovieShow show7PM = new MovieShow("7 PM");

        // Asha books A1, A2, F5[cite: 72]
        List<CinemaSeat> ashaSeats = new ArrayList<>();
        ashaSeats.add(new RegularSeat("A1"));
        ashaSeats.add(new RegularSeat("A2"));
        ashaSeats.add(new PremiumSeat("F5"));
        MovieBooking ashaBooking = counter.createBooking("Asha", show7PM, ashaSeats);

        // Ravi attempts to book A2 (already taken)[cite: 72]
        List<CinemaSeat> raviSeatFail = new ArrayList<>();
        raviSeatFail.add(new RegularSeat("A2"));
        counter.createBooking("Ravi", show7PM, raviSeatFail);

        // Ravi books R1[cite: 72]
        List<CinemaSeat> raviSeatSuccess = new ArrayList<>();
        raviSeatSuccess.add(new ReclinerSeat("R1"));
        counter.createBooking("Ravi", show7PM, raviSeatSuccess);

        // Asha cancels booking[cite: 72]
        counter.cancelBooking(ashaBooking);

        // Neha books A2 after release[cite: 72]
        List<CinemaSeat> nehaSeats = new ArrayList<>();
        nehaSeats.add(new RegularSeat("A2"));
        counter.createBooking("Neha", show7PM, nehaSeats);
    }
}