
public class ClassTopperFinderApp {

    // Simple tuple representation for (rowIndex, total)
    public static class Result {
        public final int rowIndex;
        public final int total;

        public Result(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    public static Result findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new Result(-1, 0);
        }

        int bestIndex = 0;
        int bestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }
            // Strict inequality ensures tie-breaking picks the smallest row index
            if (currentTotal > bestTotal) {
                bestTotal = currentTotal;
                bestIndex = i;
            }
        }

        return new Result(bestIndex, bestTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        Result topper = findTopper(marks);
        System.out.println("Expected Output: (1, 259)");
        System.out.println("Actual Output:   " + topper); // (1, 259)
    }
}