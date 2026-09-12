import java.util.LinkedHashMap;
import java.util.Map;

class LibraryMember {
    private String membershipPin;      // inaccessible from outside[cite: 7]
    String branchCode;                 // default / package-private[cite: 7]
    protected double finesOwed;        // same package + subclass reach[cite: 7]
    public String displayName;         // accessible anywhere[cite: 7]

    public LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class MemberAccessChecker {
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

    // Groups results per modifier regardless of which appear in the batch[cite: 7]
    public static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        Map<String, int[]> stats = new LinkedHashMap<>();

        for (String mod : modifiers) {
            stats.put(mod, new int[]{0, 0}); // index 0: allowed, index 1: denied
        }

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String mod = attempt[0].toLowerCase();
                    String outcome = classifyAccess(attempt[0], attempt[1]);
                    if (stats.containsKey(mod)) {
                        if ("ALLOWED".equals(outcome)) {
                            stats.get(mod)[0]++;
                        } else {
                            stats.get(mod)[1]++;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String mod : modifiers) {
            int[] counts = stats.get(mod);
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(String.format("%s: %d allowed / %d denied", mod, counts[0], counts[1]));
        }
        return sb.toString();
    }
}

public class LibraryMemberAccessCheckerApp {
    public static void main(String[] args) {
        System.out.println(MemberAccessChecker.classifyAccess("private", "SAME_CLASS")); // ALLOWED[cite: 7]
        System.out.println(MemberAccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")); // DENIED[cite: 7]

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(MemberAccessChecker.summarizeByModifier(attempts));
    }
}