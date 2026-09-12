class ExtendedAccessChecker {
    // Handles 5 visibility contexts including compile-time protected reach[cite: 8]
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String mod = fieldModifier.toLowerCase();

        if ("public".equals(mod)) {
            return "ALLOWED";
        }

        if ("private".equals(mod)) {
            return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
        }

        if ("default".equals(mod)) {
            return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
        }

        if ("protected".equals(mod)) {
            // Protected is accessible through inheritance cross-package ONLY via reference of the subclass's own type[cite: 8]
            switch (accessorContext) {
                case "SAME_CLASS":
                case "SAME_PACKAGE":
                case "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE":
                    return "ALLOWED";
                case "DIFFERENT_PACKAGE":
                case "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE":
                default:
                    return "DENIED";
            }
        }

        return "DENIED";
    }
}

public class SubclassTicketAccessApp {
    public static void main(String[] args) {
        System.out.println(ExtendedAccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")); // ALLOWED[cite: 8]
        System.out.println(ExtendedAccessChecker.classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")); // DENIED[cite: 8]
    }
}