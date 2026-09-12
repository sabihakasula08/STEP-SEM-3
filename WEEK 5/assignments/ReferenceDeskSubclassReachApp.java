class DeskAccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        if (fieldModifier == null || accessorContext == null) {
            return "DENIED";
        }

        String mod = fieldModifier.toLowerCase();

        if ("public".equals(mod)) {
            return "ALLOWED";
        }

        if ("private".equals(mod) || "default".equals(mod)) {
            return "DENIED"; // DENIED for all subclass-different-package contexts[cite: 7]
        }

        if ("protected".equals(mod)) {
            // Protected allows subclass access across packages ONLY via own type[cite: 7]
            if ("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        return "DENIED";
    }

    // Scans in order and stops immediately at the first denied attempt[cite: 7]
    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null) {
            return "None Denied";
        }

        for (int i = 0; i < attempts.length; i++) {
            String[] attempt = attempts[i];
            if (attempt != null && attempt.length >= 2) {
                String modifier = attempt[0];
                String context = attempt[1];
                if ("DENIED".equals(classifyAccess(modifier, context))) {
                    return String.format("%s via %s (attempt #%d)", modifier, context, i + 1); //[cite: 7]
                }
            }
        }
        return "None Denied"; //[cite: 7]
    }
}

public class ReferenceDeskSubclassReachApp {
    public static void main(String[] args) {
        String[][] attempts1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(DeskAccessChecker.firstDeniedAttempt(attempts1));

        String[][] attempts2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println(DeskAccessChecker.firstDeniedAttempt(attempts2));
    }
}