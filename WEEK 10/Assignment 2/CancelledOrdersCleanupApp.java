public class CancelledOrdersCleanupApp {

    // Custom singly linked list node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Removes all nodes matching the given code and returns the updated head
    public static Node removeAll(Node head, int code) {
        // Step 1: Remove matching nodes from the front by shifting head
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Step 2: Traverse with a predecessor pointer and skip matching nodes
        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.data == code) {
                curr.next = curr.next.next; // Bypass matching node[cite: 58]
            } else {
                curr = curr.next;
            }
        }

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    // Helper to build list from array
    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: 5 -> 3 -> 5 -> 8 -> 5, remove 5[cite: 58]
        Node orders1 = buildList(new int[]{5, 3, 5, 8, 5});
        orders1 = removeAll(orders1, 5);
        System.out.print("Sample 1 Output: ");
        printList(orders1); // 3 -> 8 -> null[cite: 58]

        // Sample 2: 5 -> 5 -> 5, remove 5[cite: 58]
        Node orders2 = buildList(new int[]{5, 5, 5});
        orders2 = removeAll(orders2, 5);
        System.out.print("Sample 2 Output: ");
        printList(orders2); // null[cite: 58]
    }
}