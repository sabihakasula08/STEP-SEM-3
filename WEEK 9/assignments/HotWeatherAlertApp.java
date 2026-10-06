public class HotWeatherAlertApp {

    // Sliding Window: compares sum >= k * threshold to avoid precision loss[cite: 47]
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        long requiredSum = (long) k * threshold; // Using long to guard against integer overflow[cite: 47]
        long windowSum = 0;
        int alertCount = 0;

        // Build first window of size k[cite: 47]
        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        if (windowSum >= requiredSum) {
            alertCount++;
        }

        // Slide window one element at a time[cite: 47]
        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i] - readings[i - k]; // Add incoming, subtract outgoing[cite: 47]
            if (windowSum >= requiredSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        int alerts = countAlerts(readings, k, threshold);
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output:   " + alerts); // 3[cite: 47]
    }
}