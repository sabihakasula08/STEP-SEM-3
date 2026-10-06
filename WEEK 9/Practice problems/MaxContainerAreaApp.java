public class MaxContainerAreaApp {

    // Two-Pointer Optimal Approach
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int height = Math.min(heights[left], heights[right]);
            int currentArea = height * width; //

            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            // Move the pointer pointing to the shorter boundary inward
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxContainerArea(heights);

        System.out.println("Expected Output: 49");
        System.out.println("Actual Output:   " + result); // 49
    }
}