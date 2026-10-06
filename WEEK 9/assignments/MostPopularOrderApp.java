import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostPopularOrderApp {

    public static class OrderResult {
        public final String item;
        public final int count;

        public OrderResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    public static OrderResult mostPopular(List<String> orders) {
        if (orders == null || orders.isEmpty()) {
            return new OrderResult("", 0);
        }

        // Pass 1: Frequency map
        Map<String, Integer> frequencyMap = new HashMap<>();
        int maxCount = 0;
        for (String item : orders) {
            int count = frequencyMap.getOrDefault(item, 0) + 1;
            frequencyMap.put(item, count);
            if (count > maxCount) {
                maxCount = count;
            }
        }

        // Pass 2: Re-scan in original order to break ties with the first appearance
        for (String item : orders) {
            if (frequencyMap.get(item) == maxCount) {
                return new OrderResult(item, maxCount);
            }
        }

        return new OrderResult("", 0);
    }

    public static void main(String[] args) {
        // Sample 1
        List<String> orders1 = Arrays.asList("dosa", "idli", "vada", "dosa", "idli", "dosa", "tea");
        System.out.println("Sample 1 Output: " + mostPopular(orders1)); // ("dosa", 3)

        // Sample 2 (tie-breaking test)
        List<String> orders2 = Arrays.asList("tea", "coffee", "coffee", "tea");
        System.out.println("Sample 2 Output: " + mostPopular(orders2)); // ("tea", 2)
    }
}