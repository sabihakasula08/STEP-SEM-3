public class ReverseQueueApp {

    // Custom singly linked list node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // In-place iterative reversal using three references
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;    // Store next node
            current.next = prev;    // Reverse pointer[cite: 59]
            prev = current;         // Shift previous forward[cite: 59]
            current = next;         // Shift current forward[cite: 59]
        }

        return prev; // New head of reversed list[cite: 59]
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
        // Sample 1: 1 -> 2 -> 3 -> 4[cite: 59]
        Node queue1 = buildList(new int[]{1, 2, 3, 4});
        queue1 = reverse(queue1);
        System.out.print("Sample 1 Output: ");
        printList(queue1); // 4 -> 3 -> 2 -> 1 -> null[cite: 59]

        // Sample 2: 9[cite: 59]
        Node queue2 = buildList(new int[]{9});
        queue2 = reverse(queue2);
        System.out.print("Sample 2 Output: ");
        printList(queue2); // 9 -> null[cite: 59]
    }
}