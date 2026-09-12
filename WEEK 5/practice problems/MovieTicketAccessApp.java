
class MovieTicket {
    // Encapsulated fields choosing intentional access levels per visibility requirements
    private String seatNumber;      // accessible only within this class
    String screenId;                // package-private (default): accessible in same package
    protected double ticketPrice;   // accessible in same package and cross-package subclasses
    public String movieTitle;       // accessible everywhere

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
    }
}

class AccessChecker {
    // Evaluates visibility according to real Java language rules[cite: 8]
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        switch (fieldModifier.toLowerCase()) {
            case "public":
                return "ALLOWED";
            case "protected":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
            case "default":
                return (accessorContext.equals("SAME_CLASS") || accessorContext.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";
            case "private":
                return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
            default:
                return "DENIED";
        }
    }

    // Summarizes allowed vs denied counts across a batch[cite: 8]
    public static String summarizeBatch(String[][] attempts) {
        int allowedCount = 0;
        int deniedCount = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String result = classifyAccess(attempt[0], attempt[1]);
                    if ("ALLOWED".equals(result)) {
                        allowedCount++;
                    } else {
                        deniedCount++;
                    }
                }
            }
        }
        return "Allowed: " + allowedCount + "\nDenied: " + deniedCount;
    }
}

public class MovieTicketAccessApp {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS")); // ALLOWED[cite: 8]
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED[cite: 8]

        String[][] testBatch = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(AccessChecker.summarizeBatch(testBatch));
    }
}