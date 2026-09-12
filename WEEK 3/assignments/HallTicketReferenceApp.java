class HallTicket {
    String studentName;
    int seatNumber;

    public HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

public class HallTicketReferenceApp {
    public static void main(String[] args) {
        // Original object instance
        HallTicket priya = new HallTicket("Priya", 0);

        // Reference copy pointing to the same memory allocation
        HallTicket copy = priya;
        copy.seatNumber = 45;

        // Verify that mutating 'copy' directly altered 'priya'
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));

        // Distinct object with matching data values
        HallTicket separate = new HallTicket("Priya", 45);
        System.out.println("separate == priya: " + (separate == priya));
    }
}