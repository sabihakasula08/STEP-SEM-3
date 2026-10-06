public class WarehouseGridSummaryApp {

    public static class Coordinate {
        public final int row;
        public final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    public static class WarehouseResult {
        public final int totalItems;
        public final Coordinate maxCoordinate;

        public WarehouseResult(int totalItems, Coordinate maxCoordinate) {
            this.totalItems = totalItems;
            this.maxCoordinate = maxCoordinate;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", " + maxCoordinate + ")";
        }
    }

    public static WarehouseResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new WarehouseResult(0, new Coordinate(0, 0));
        }

        int total = 0;
        int maxVal = -1;
        int maxRow = 0;
        int maxCol = 0;

        // Scan row by row, left to right
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                total += grid[r][c]; //
                // Strict inequality preserves the first encountered max bin
                if (grid[r][c] > maxVal) {
                    maxVal = grid[r][c];
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new WarehouseResult(total, new Coordinate(maxRow, maxCol));
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        WarehouseResult result = warehouseSummary(grid);
        System.out.println("Expected Output: (49, (2, 1))");
        System.out.println("Actual Output:   " + result); // (49, (2, 1))[cite: 50]
    }
}